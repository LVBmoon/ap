package ap.exercises.finalTerm_project.version1.menu;

import ap.exercises.finalTerm_project.version1.core.Borrow;
import ap.exercises.finalTerm_project.version1.core.BorrowRequest;
import ap.exercises.finalTerm_project.version1.core.Library;
import ap.exercises.finalTerm_project.version1.data.DataStorage;
import ap.exercises.finalTerm_project.version1.data.InputProcessor;
import ap.exercises.finalTerm_project.version1.menu.Menu;
import ap.exercises.finalTerm_project.version1.model.Book;
import ap.exercises.finalTerm_project.version1.model.Librarian;
import ap.exercises.finalTerm_project.version1.model.Student;
import java.time.LocalDate;
import java.util.List;

public class StudentMenu extends Menu {
    private Student student;

    public StudentMenu(Library library, InputProcessor inputProcessor) {
        super(library, inputProcessor);
        this.student = null;
    }

    @Override
    public void showMenu() {
        while (true) {
            if (student == null) {

                System.out.println("\n=== Student Menu ===");
                System.out.println("   1. Register");
                System.out.println("   2. Login");
                System.out.println("   3. Exit");
                int choice = inputProcessor.getIntInput("Please enter choice: ");
                switch (choice) {
                    case 1:
                        register();
                        break;
                    case 2:
                        login();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } else {
                System.out.println("\n--- Student Menu ---\nWelcome " + student.getFirstName() + " " + student.getLastName() + "!");
                System.out.println("   1. Search Books");
                System.out.println("   2. Request Borrow");
                System.out.println("   3. View Active Borrows");
                System.out.println("   4. Request Return");
                System.out.println("   5. View Borrow History");
                System.out.println("   6. View Request Status");
                System.out.println("   7. Change Password");
                System.out.println("   8. Logout");
                int choice = inputProcessor.getIntInput("Please enter choice: ");
                switch (choice) {
                    case 1:
                        searchBooks();
                        break;
                    case 2:
                        requestBorrow();
                        break;
                    case 3:
                        viewActiveBorrows();
                        break;
                    case 4:
                        requestReturn();
                        break;
                    case 5:
                        viewBorrowHistory();
                        break;
                    case 6:
                        viewRequestStatus();
                        break;
                    case 7:
                        changePassword();
                        break;
                    case 8:
                        student = null;
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
            new DataStorage().saveLibrary(library);
        }
    }

    private void register() {
        String firstName = inputProcessor.getStringInput("Enter first name: ");
        String lastName = inputProcessor.getStringInput("Enter last name: ");
        String studentId = inputProcessor.getStringInput("Enter student ID: ");
        String fieldOfStudy = inputProcessor.getStringInput("Enter field of study: ");
        String username = inputProcessor.getStringInput("Enter username: ");
        String password = inputProcessor.getStringInput("Enter password: ");

        Student existing = library.findStudentByStudentId(studentId);
        if (existing != null) {
            System.out.println("Student ID already exists!");
            return;
        }
        for (Student s : library.getStudents()) {
            if (s.getUsername().equals(username)) {
                System.out.println("Username already exists!");
                return;
            }
        }

        try {
            Student newStudent = new Student(firstName, lastName, studentId, fieldOfStudy, username, password, library);
            library.addStudent(newStudent);
            System.out.println("Registration successful.");
        }
        catch (IllegalArgumentException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    private void login() {
        if (library.getStudents().isEmpty()) {
            System.out.println("No students loaded. Please register or check data files (students.txt).");
            return;
        }
        String username = inputProcessor.getStringInput("Enter username: ");
        String password = inputProcessor.getStringInput("Enter password: ");
        for (Student s : library.getStudents()) {
            if (s.getUsername().equals(username) && s.checkPassword(password)) {
                student = s;
                System.out.println("Login successful.");
                return;
            }
        }
        System.out.println("Invalid username or password.");
    }

    private void searchBooks() {
        String title = inputProcessor.getStringInput("Enter book title (*or press enter to skip): ");
        String yearStr = inputProcessor.getStringInput("Enter publication year (*or press enter to skip): ");
        Integer year = yearStr.isEmpty() ? null : Integer.parseInt(yearStr);
        String author = inputProcessor.getStringInput("Enter author (or press enter to skip): ");
        List<Book> results = library.searchBooks(title.isEmpty() ? null : title, year, author.isEmpty() ? null : author);
        if (results.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("Search Results:");
            for (Book book : results) {
                System.out.println(book);
            }
        }
    }

    private void requestBorrow() {
        String bookId = inputProcessor.getStringInput("Enter book ID to borrow: ");
        Book book = library.findBookByBookId(bookId);
        if (book == null) {
            System.out.println("No book found with that ID.");
            return;
        }
        if (!student.isActive()) {
            System.out.println("Your account is deactivated and cannot borrow books.");
            return;
        }
        if (student.getBorrowedBooks().size() >= 5) {
            System.out.println("You have reached the maximum borrow limit (5 books).");
            return;
        }
        if (!book.isAvailable()) {
            System.out.println("Book is not available.");
            return;
        }
        Librarian librarian = library.getRandomLibrarian();
        BorrowRequest request = new BorrowRequest(book, student, librarian, LocalDate.now(), LocalDate.now().plusDays(14), false);
        library.addBorrowRequest(request);
        System.out.println("Borrow request sent to " + librarian.getFirstName() + " " + librarian.getLastName() + ".");
    }

    private void requestReturn() {
        String bookId = inputProcessor.getStringInput("Enter book ID to return: ");
        Borrow borrow = null;
        for (Borrow b : library.getBorrows()) {
            if (b.getBook().getBookId().equalsIgnoreCase(bookId) &&
                    b.getStudent().getStudentId().equals(student.getStudentId()) &&
                    !b.isReturned()) {
                borrow = b;
                break;
            }
        }
        if (borrow == null) {
            System.out.println("No active borrow found for this book!");
            return;
        }
        Librarian librarian = library.getRandomLibrarian();
        BorrowRequest request = new BorrowRequest(borrow.getBook(), student, librarian, LocalDate.now(), borrow.getDueDate(), true);
        library.addBorrowRequest(request);
        System.out.println("Return request sent to " + librarian.getFirstName() + " " + librarian.getLastName() + ".");
    }

    private void viewRequestStatus() {
        boolean found = false;
        System.out.println("Your Request Status:");
        for (BorrowRequest request : library.getBorrowRequests()) {
            if (request.getStudent().getStudentId().equals(student.getStudentId())) {
                System.out.println(request);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No requests found.");
        }
    }


    private void viewActiveBorrows() {
        boolean found = false;
        System.out.println("Active Borrows:");
        for (Borrow borrow : library.getBorrows()) {
            if (borrow.getStudent().getStudentId().equals(student.getStudentId()) && !borrow.isReturned()) {
                System.out.println(borrow);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No active borrows.");
        }
    }

    private void viewBorrowHistory() {
        boolean found = false;
        System.out.println("Borrow History:");
        for (Borrow borrow : library.getBorrows()) {
            if (borrow.getStudent().getStudentId().equals(student.getStudentId())) {
                System.out.println(borrow);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No borrow history.");
        }
    }


    private void changePassword() {
        String currentPass = inputProcessor.getStringInput("Enter current password: ");
        if (!student.checkPassword(currentPass)) {
            System.out.println("Incorrect current password!");
            return;
        }
        String newPass = inputProcessor.getStringInput("Enter new password: ");
        try {
            student.setPassword(newPass);
            System.out.println("Password changed successfully!");
        }
        catch (IllegalArgumentException e) {
            System.out.println("Password change failed: " + e.getMessage());
        }
    }
}
