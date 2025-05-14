package ap.exercises.midTerm_project.version1;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class LibraryMenu {
    private Library library;
    private Scanner scanner;
    private Random random;

    public LibraryMenu(Library library) {
        this.library = library;
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }
//I didn't make an account for librarian and in this version like reality the admin should add students
    public void showMainMenu() {
        System.out.println("\n=== University Library System ===");
        System.out.println("1. login as student");
        System.out.println("2. login as admin");
        System.out.println("3. Exit");
        System.out.print("Enter your choice : ");
    }

    public void showStudentMenu() {
        System.out.println("\n=== Student Menu ===");
        System.out.println("1. search books");
        System.out.println("2. loan books");
        System.out.println("3. return books");
        System.out.println("4. show the loan list");
        System.out.println("5. return to the main list");
        System.out.print("Enter your choice :  ");
    }

    public void showManagerMenu() {
        System.out.println("\n=== Admin Menu ===");
        System.out.println("1. Adding new book");
        System.out.println("2. Adding new student");
        System.out.println("3. Show all the delay books");
        System.out.println("4. Librarian statistics");
        System.out.println("5. Show the most loan books");
        System.out.println("6. Return to the main menu ");
        System.out.print("please enter your choice : ");
    }

    public void handleStudentOperations() {
        System.out.print("\nPlease enter your ID: ");
        String studentId = scanner.nextLine();

        Optional<Student> optionalStudent = library.getStudents().stream()
                .filter(s -> s.getStudentId().equals(studentId))
                .findFirst();

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            System.out.println("welcome " + student.getFirstName() + " " + student.getLastName());

            while (true) {
                showStudentMenu();
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        searchBooks();
                        break;
                    case 2:
                        borrowBook(student);
                        break;
                    case 3:
                        returnBook(student);
                        break;
                    case 4:
                        showBorrowedBooks(student);
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("invalid choice!");
                }
            }
        } else {
            System.out.println("Couldn't find student with ID " + studentId);
        }
    }

    private void searchBooks() {
        System.out.print("\nEnter the book name to search: : ");
        String keyword = scanner.nextLine();

        List<Book> results = library.searchBooks(keyword);
        if (results.isEmpty()) {
            System.out.println("Couldn't find any book with name " + keyword);
        } else {
            System.out.println("\nSearch results: :");
            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + results.get(i));
            }
        }
    }

    private void borrowBook(Student student) {
        System.out.print("\nPlease enter the name of the bok you want to loan : ");
        String title = scanner.nextLine();

        List<Book> availableBooks = library.getBooks().stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title) && book.isAvailable())
                .collect(Collectors.toList());

        if (availableBooks.isEmpty()) {
            System.out.println("The book with this title isn't available!");
            return;
        }

        Book bookToBorrow = availableBooks.get(0);
        Librarian librarian = getRandomLibrarian();

        if (library.borrowBook(student, bookToBorrow, librarian)) {
            System.out.println("loaned successfully!");
            System.out.println("librarian: " + librarian);
            System.out.println("return date : " + LocalDate.now().plusDays(14));
        } else {
            System.out.println("An error occured! You can't borrow this book!");
        }
    }

    private void returnBook(Student student) {
        List<Book> borrowedBooks = student.getBorrowedBooks();
        if (borrowedBooks.isEmpty()) {
            System.out.println("You didnt borrow any books!");
            return;
        }

        System.out.println("\nborrowed books: :");
        for (int i = 0; i < borrowedBooks.size(); i++) {
            System.out.println((i + 1) + ". " + borrowedBooks.get(i).getTitle());
        }

        System.out.print("please enter the number of book you want to return: ");
        int bookIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (bookIndex < 0 || bookIndex >= borrowedBooks.size()) {
            System.out.println("Invalid number!");
            return;
        }

        Book bookToReturn = borrowedBooks.get(bookIndex);
        Librarian librarian = getRandomLibrarian();

        if (library.returnBook(student, bookToReturn, librarian)) {
            System.out.println("returned successfully!");
            System.out.println("librarian: " + librarian);
        } else {
            System.out.println("An error ocurd! You couldn't return this book!");
        }
    }

    private void showBorrowedBooks(Student student) {
        List<Book> borrowedBooks = student.getBorrowedBooks();
        if (borrowedBooks.isEmpty()) {
            System.out.println("You didn't borrow any books!");
        } else {
            System.out.println("\nBorrowed books: ");
            for (Book book : borrowedBooks) {
                System.out.println("- " + book);
            }
        }
    }

    public void handleManagerOperations() {
        System.out.println("\nWelcome " + library.getManager());

        while (true) {
            showManagerMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addNewBook();
                    break;
                case 2:
                    registerNewStudent();
                    break;
                case 3:
                    showOverdueLoans();
                    break;
                case 4:
                    showLibrarianStatistics();
                    break;
                case 5:
                    showMostBorrowedBooks();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("invalid choice!");
            }
        }
    }

    private void addNewBook() {
        System.out.println("\nAdding new book:");
        System.out.print("title: ");
        String title = scanner.nextLine();
        System.out.print("author: ");
        String author = scanner.nextLine();
        System.out.print("publication year: ");
        int year = scanner.nextInt();
        System.out.print("pages : ");
        int pages = scanner.nextInt();
        scanner.nextLine(); // Consume newline to avoide scanner errors

        Book newBook = new Book(title, author, year, pages);
        library.addBook(newBook);
        System.out.println("Book added successfully!");
    }

    private void registerNewStudent() {
        System.out.println("\nAdding new student:");
        System.out.print("First name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Major : ");
        String major = scanner.nextLine();

        Student newStudent = new Student(firstName, lastName, studentId, major);
        library.registerStudent(newStudent);
        System.out.println("Student added successfully!");
    }

    private void showOverdueLoans() {
        List<Loan> overdueLoans = library.getOverdueLoans();
        if (overdueLoans.isEmpty()) {
            System.out.println("No books has returned overdue!");
        } else {
            System.out.println("\nBooks overdue loans:");
            for (Loan loan : overdueLoans) {
                System.out.println("- " + loan.getBook().getTitle() +
                        " by " + loan.getStudent() +
                        " (return time: " + loan.getDueDate() + ")");
            }
        }
    }

    private void showLibrarianStatistics() {
        Map<Librarian, Integer> stats = library.getLibrarianStatistics();
        if (stats.isEmpty()) {
            System.out.println("No statistics available!");
        } else {
            System.out.println("\nLibrarian statistics :");
            for (Map.Entry<Librarian, Integer> entry : stats.entrySet()) {
                System.out.println("- " + entry.getKey() + ": " + entry.getValue() + " loan");
            }
        }
    }

    private void showMostBorrowedBooks() {
        List<Book> mostBorrowed = library.getMostBorrowedBooks(10);
        if (mostBorrowed.isEmpty()) {
            System.out.println("No statistics available!");
        } else {
            System.out.println("\nThe most borrowed books :");
            for (int i = 0; i < mostBorrowed.size(); i++) {
                System.out.println((i + 1) + ". " + mostBorrowed.get(i));
            }
        }
    }

    private Librarian getRandomLibrarian() {
        List<Librarian> librarians = library.getLibrarians();
        return librarians.get(random.nextInt(librarians.size()));
    }
}
