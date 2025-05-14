package ap.exercises.midTerm_project.version2_part1;
//I made this one less complicated toward the previous ones :)
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private Library library;
    private Student currentStudent;
    private Librarian currentLibrarian;
    private LibraryManager currentManager;

    public Menu(Library library) {
        this.scanner = new Scanner(System.in);
        this.library = library;
    }

    public void showMainMenu() {
        while (true) {
            System.out.println("\n*** University Library Management System ***\n 1. Login as Student\n 2. Login as Librarian\n" +
                    " 3. Login as Manager\n 4. Register as New Student\n 0. Exit Program\nPlease select an option: ");
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    studentLogin();
                    break;
                case 2:
                    librarianLogin();
                    break;
                case 3:
                    managerLogin();
                    break;
                case 4:
                    registerStudent();
                    break;
                case 0:
                    System.out.println("Saving data and exiting program...");
                    library.saveToFile("library_data.ser");
                    System.exit(0);
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a number: ");
            }
        }
    }

    private void studentLogin() {
        System.out.print("\n*Student username: ");
        String username = scanner.nextLine();
        System.out.print("*Password: ");
        String password = scanner.nextLine();

        Student student = library.findStudentByUsername(username);
        if (student != null && student.checkPassword(password)) {
            currentStudent = student;
            showStudentMenu();
        } else {
            System.out.println("Invalid username or password!");
        }
    }

    private void librarianLogin() {
        System.out.print("\nLibrarian username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        Librarian librarian = library.findLibrarianByUsername(username);
        if (librarian != null && librarian.checkPassword(password)) {
            currentLibrarian = librarian;
            showLibrarianMenu();
        } else {
            System.out.println("Invalid username or password!");
        }
    }

    private void managerLogin() {
        System.out.print("\nManager username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (library.getManager().getUsername().equals(username) &&
                library.getManager().checkPassword(password)) {
            currentManager = library.getManager();
            showManagerMenu();
        } else {
            System.out.println("Invalid username or password!");
        }
    }

    private void registerStudent() {
        System.out.println("\n=== New Student Registration ===");

        System.out.print("First name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Field of study(Major): ");
        String fieldOfStudy = scanner.nextLine();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        // Check if username already exists then prevent to chose it again
        if (library.findStudentByUsername(username) != null) {
            System.out.println("Username already exists!");
            return;
        }

        try {
            Student newStudent = new Student(firstName, lastName, studentId, fieldOfStudy, new Date(), username, password);
            library.addStudent(newStudent);
            System.out.println("Registration successful!");
        } catch (IllegalArgumentException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    private void showStudentMenu() {
        while (currentStudent != null) {
            System.out.println("\n=== Student Menu ===\n 1. Search for a Book\n 2. Request to Borrow a Book\n " +
                    "3. View My Borrowed Books\n 4. View My Loan History\n 5. Change Password\n " +
                    "0. Logout\nPlease select an option: ");
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    searchBooks();
                    break;
                case 2:
                    requestBookLoan();
                    break;
                case 3:
                    showBorrowedBooks();
                    break;
                case 4:
                    showLoanHistory();
                    break;
                case 5:
                    changeStudentPassword();
                    break;
                case 0:
                    currentStudent = null;
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private void showLibrarianMenu() {
        while (currentLibrarian != null) {
            System.out.println("\n=== Librarian Menu ===\n 1. Add New Book\n 2. Process Loan Requests\n 3. Process Book Returns" +
                    "\n 4. View My Processed Loans\n 5. View Student Loan History\n 6. Change Password\n 0. Logout\n" +
                    "Please select an option: ");
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    addNewBook();
                    break;
                case 2:
                    processLoanRequests();
                    break;
                case 3:
                    processBookReturn();
                    break;
                case 4:
                    showProcessedLoans();
                    break;
                case 5:
                    showStudentLoans();
                    break;
                case 6:
                    changeLibrarianPassword();
                    break;
                case 0:
                    currentLibrarian = null;
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private void showManagerMenu() {
        while (currentManager != null) {
            System.out.println("\n=== Manager Menu ===\n1. Add New Librarian\n 2. View Overdue Loans Report\n " +
                    "3. View Librarian Statistics\n 4. View Most Borrowed Books\n 5. Change Password\n 0. Logout\n" +
                    "Please select an option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    addNewLibrarian();
                    break;
                case 2:
                    showOverdueLoans();
                    break;
                case 3:
                    showLibrarianStatistics();
                    break;
                case 4:
                    showMostBorrowedBooks();
                    break;
                case 5:
                    changeManagerPassword();
                    break;
                case 0:
                    currentManager = null;
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    // Student operations :
    private void searchBooks() {
        System.out.print("\nEnter book title to search: ");
        String title = scanner.nextLine();
        Book book = library.findBookByTitle(title);
        if (book != null) {
            System.out.println("Book found:");
            System.out.println(book);
        } else {
            System.out.println("No book found with that title.");
        }
    }

    private void requestBookLoan() {
        System.out.print("\nEnter book title to borrow: ");
        String title = scanner.nextLine();
        Book book = library.findBookByTitle(title);

        if (book == null) {
            System.out.println("No book found with that title.");
            return;
        }

        if (currentStudent.borrowBook(book)) {
            Loan newLoan = new Loan(book, currentStudent,
                    library.getLibrarians().get(0), // Random librarian
                    new Date());
            library.addLoan(newLoan);
            System.out.println("Book borrowed successfully for 2 weeks!");
        } else {
            System.out.println("Cannot borrow book (either not available or you've reached your limit of 5 books).");
        }
    }

    private void showBorrowedBooks() {
        ArrayList<Book> borrowedBooks = currentStudent.getBorrowedBooks();
        if (borrowedBooks.isEmpty()) {
            System.out.println("You have no borrowed books.");
            return;
        }

        System.out.println("\nYour Borrowed Books:");
        for (Book book : borrowedBooks) {
            System.out.println(book);
        }
    }

    private void showLoanHistory() {
        System.out.println("\nYour Loan History:");
        boolean found = false;
        for (Loan loan : library.getLoans()) {
            if (loan.getStudent().getUsername().equals(currentStudent.getUsername())) {
                System.out.println(loan);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No loan history found.");
        }
    }

    private void changeStudentPassword() {
        System.out.print("\nCurrent password: ");
        String currentPass = scanner.nextLine();
        if (!currentStudent.checkPassword(currentPass)) {
            System.out.println("Incorrect current password!");
            return;
        }

        System.out.print("New password: ");
        String newPass = scanner.nextLine();

        try {
            currentStudent.setPassword(newPass);
            System.out.println("Password changed successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Password change failed: " + e.getMessage());
        }
    }

    // Librarian operations :
    private void addNewBook() {
        System.out.println("\n=== Add New Book ===");
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("Publication Year: ");
        int year = getIntInput();
        System.out.print("Page Count: ");
        int pages = getIntInput();
        scanner.nextLine(); // Consume newline

        try {
            Book newBook = new Book(title, author, year, pages);
            library.addBook(newBook);
            System.out.println("Book added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to add book: " + e.getMessage());
        }
    }

    private void processLoanRequests() {
        System.out.println("\n=== Process Loan Requests ===");
        System.out.println("No pending requests in this simplified system.");
    }

    private void processBookReturn() {
        System.out.println("\n=== Process Book Return ===");
        System.out.print("Enter student username: ");
        String username = scanner.nextLine();

        Student student = library.findStudentByUsername(username);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        ArrayList<Book> borrowedBooks = student.getBorrowedBooks();
        if (borrowedBooks.isEmpty()) {
            System.out.println("This student has no borrowed books.");
            return;
        }

        System.out.println("Borrowed books:");
        for (int i = 0; i < borrowedBooks.size(); i++) {
            System.out.println((i+1) + ". " + borrowedBooks.get(i));
        }

        System.out.print("Select book to return (1-" + borrowedBooks.size() + "): ");
        int choice = getIntInput();
        if (choice < 1 || choice > borrowedBooks.size()) {
            System.out.println("Invalid selection!");
            return;
        }

        Book bookToReturn = borrowedBooks.get(choice-1);
        if (student.returnBook(bookToReturn)) {
            // Find and update the loan record
            for (Loan loan : library.getLoans()) {
                if (loan.getBook() == bookToReturn && !loan.isReturned()) {
                    loan.returnBook(currentLibrarian, new Date());
                    System.out.println("Book returned successfully!");
                    return;
                }
            }
            System.out.println("Book returned but loan record not found!");
        } else {
            System.out.println("Failed to return book.");
        }
    }

    private void showProcessedLoans() {
        System.out.println("\nLoans Processed by You:");
        boolean found = false;
        for (Loan loan : library.getLoans()) {
            if ((loan.getIssuedBy() == currentLibrarian ||
                    (loan.isReturned() && loan.getReceivedBy() == currentLibrarian))) {
                System.out.println(loan);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No loans processed by you found.");
        }
    }

    private void showStudentLoans() {
        System.out.print("\nEnter student username: ");
        String username = scanner.nextLine();

        Student student = library.findStudentByUsername(username);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.println("\nLoan History for " + student.getFirstName() + " " + student.getLastName() + ":");
        boolean found = false;
        for (Loan loan : library.getLoans()) {
            if (loan.getStudent().getUsername().equals(username)) {
                System.out.println(loan);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No loan history found for this student.");
        }
    }

    private void changeLibrarianPassword() {
        System.out.print("\nCurrent password: ");
        String currentPass = scanner.nextLine();
        if (!currentLibrarian.checkPassword(currentPass)) {
            System.out.println("Incorrect current password!");
            return;
        }

        System.out.print("New password: ");
        String newPass = scanner.nextLine();

        try {
            currentLibrarian.setPassword(newPass);
            System.out.println("Password changed successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Password change failed: " + e.getMessage());
        }
    }

    // Manager operations
    private void addNewLibrarian() {
        System.out.println("\n=== Add New Librarian ===");
        System.out.print("First name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Employee ID: ");
        String employeeId = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        // Check if username already exists
        if (library.findLibrarianByUsername(username) != null) {
            System.out.println("Username already exists!");
            return;
        }

        try {
            Librarian newLibrarian = new Librarian(firstName, lastName, employeeId, username, password);
            library.addLibrarian(newLibrarian);
            System.out.println("Librarian added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to add librarian: " + e.getMessage());
        }
    }

    private void showOverdueLoans() {
        ArrayList<Loan> overdueLoans = library.getOverdueLoans();
        if (overdueLoans.isEmpty()) {
            System.out.println("\nNo overdue loans found.");
            return;
        }

        System.out.println("\nOverdue Loans:");
        for (Loan loan : overdueLoans) {
            System.out.println(loan);
        }
    }

    private void showLibrarianStatistics() {
        System.out.println("\nLibrarian Statistics:");
        for (Librarian librarian : library.getLibrarians()) {
            System.out.println(librarian.getFirstName() + " " + librarian.getLastName() +
                    ": " + librarian.getProcessedLoans() + " loans processed");
        }
    }

    private void showMostBorrowedBooks() {
        ArrayList<Book> mostBorrowed = library.getMostBorrowedBooks(2023, 10);
        System.out.println("\nMost Borrowed Books (last year):");
        for (Book book : mostBorrowed) {
            System.out.println(book.getTitle() + " by " + book.getAuthor());
        }
    }

    private void changeManagerPassword() {
        System.out.print("\nCurrent password: ");
        String currentPass = scanner.nextLine();
        if (!currentManager.checkPassword(currentPass)) {
            System.out.println("Incorrect current password!");
            return;
        }

        System.out.print("New password: ");
        String newPass = scanner.nextLine();

        try {
            currentManager.setPassword(newPass);
            System.out.println("Password changed successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Password change failed: " + e.getMessage());
        }
    }
}
