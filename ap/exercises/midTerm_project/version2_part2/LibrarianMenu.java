package ap.exercises.midTerm_project.version2_part2;

import java.time.LocalDate;
import java.util.Scanner;

public class LibrarianMenu extends Menu {
    private Librarian librarian;

    public LibrarianMenu(Library library, Scanner scanner, InputProcessor inputProcessor) {
        super(library, scanner, inputProcessor);
        this.librarian = null;
    }

    @Override
    public void showMenu() {
        while (true) {
            if (librarian == null) {
                System.out.println("\n=== Librarian Menu ===");
                System.out.println("   1. Login");
                System.out.println("   2. Exit");
                int choice = inputProcessor.getIntInput("Please enter choice: ");
                switch (choice) {
                    case 1:
                        login();
                        break;
                    case 2:
                        return;
                    default:
                        System.out.println("\nInvalid choice! Try again.");
                }
            } else {
                System.out.println("\n--- Librarian Menu ---\nWelcome " + librarian.getFirstName() + " " + librarian.getLastName() + " !");
                System.out.println("   1. Edit Personal Info");
                System.out.println("   2. Add New Book");
                System.out.println("   3. Confirm Borrow");
                System.out.println("   4. Confirm Return");
                System.out.println("   5. View Processed Borrows");
                System.out.println("   6. View Student Borrow History");
                System.out.println("   7. Change Password");
                System.out.println("   8. Logout");
                int choice = inputProcessor.getIntInput("Please enter choice: ");
                switch (choice) {
                    case 1:
                        editPersonalInfo();
                        break;
                    case 2:
                        addNewBook();
                        break;
                    case 3:
                        confirmBorrow();
                        break;
                    case 4:
                        confirmReturn();
                        break;
                    case 5:
                        viewProcessedBorrows();
                        break;
                    case 6:
                        viewStudentHistory();
                        break;
                    case 7:
                        changePassword();
                        break;
                    case 8:
                        librarian = null;
                        break;
                    default:
                        System.out.println("\nInvalid choice! `");
                }
            }
        }
    }

    private void login() {
        String username = inputProcessor.getStringInput("Enter username: ");
        String password = inputProcessor.getStringInput("Enter password: ");
        for (int i = 0; i < library.getLibrarians().size(); i++) {
            Librarian l = library.getLibrarians().get(i);
            if (l.getUsername().equals(username) && l.checkPassword(password)) {
                librarian = l;
                System.out.println("\nLogin successful.");
                return;
            }
        }
        System.out.println("\nInvalid username or password!");
    }

    private void editPersonalInfo() {
        String phoneNumber = inputProcessor.getStringInput("Enter phone number (e.g. +989123456789, or press enter to skip): ");
        String address = inputProcessor.getStringInput("Enter address (or press enter to skip): ");
        String educationLevel = inputProcessor.getStringInput("Enter education level (e.g. BSc, or press enter to skip): ");
        try {
            librarian.setPhoneNumber(phoneNumber);
            librarian.setAddress(address);
            if (!educationLevel.isEmpty()) {
                librarian.setEducationLevel(educationLevel);
            }
            System.out.println("\nPersonal info updated.");
        } catch (IllegalArgumentException e) {
            System.out.println("\nFailed to update info: " + e.getMessage());
        }
    }

    private void addNewBook() {
        String title = inputProcessor.getStringInput("Enter book title: ");
        String author = inputProcessor.getStringInput("Enter author: ");
        int year = inputProcessor.getIntInput("Enter publication year: ");
        int pages = inputProcessor.getIntInput("Enter number of pages: ");
        try {
            Book book = new Book(title, author, year, pages);
            library.addBook(book);
            System.out.println("\nBook added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("\nFailed to add book: " + e.getMessage());
        }
    }

    private void confirmBorrow() {
        String studentId = inputProcessor.getStringInput("Enter student ID: ");
        String title = inputProcessor.getStringInput("Enter book title: ");
        Student student = library.findStudentByStudentId(studentId);
        Book book = library.findBookByTitle(title);
        if (student == null || book == null) {
            System.out.println("\nStudent or book not found!");
            return;
        }
        if (student.borrowBook(book)) {
            Borrow borrow = new Borrow(book, student, librarian, LocalDate.now());
            library.addBorrow(borrow);
            System.out.println("Borrow confirmed.");
        } else {
            System.out.println("\nCannot borrow book (either not available or student has reached limit).");
        }
    }

    private void confirmReturn() {
        String studentId = inputProcessor.getStringInput("Enter student ID: ");
        String title = inputProcessor.getStringInput("Enter book title: ");
        Borrow borrow = null;
        for (int i = 0; i < library.getBorrows().size(); i++) {
            Borrow b = library.getBorrows().get(i);
            if (b.getBook().getTitle().equalsIgnoreCase(title) &&
                    b.getStudent().getStudentId().equals(studentId) &&
                    !b.isReturned()) {
                borrow = b;
                break;
            }
        }
        if (borrow == null) {
            System.out.println("\nNo active borrow found!");
            return;
        }
        Book book = borrow.getBook();
        if (borrow.getStudent().returnBook(book)) {
            borrow.returnBook(librarian, LocalDate.now());
            System.out.println("\nReturn confirmed.");
        } else {
            System.out.println("\nFailed to return book.");
        }
    }

    private void viewProcessedBorrows() {
        boolean found = false;
        System.out.println("Processed Borrows:");
        for (int i = 0; i < library.getBorrows().size(); i++) {
            Borrow borrow = library.getBorrows().get(i);
            if (borrow.getIssuedBy().getEmployeeId().equals(librarian.getEmployeeId()) ||
                    (borrow.isReturned() && borrow.getReceivedBy().getEmployeeId().equals(librarian.getEmployeeId()))) {
                System.out.println(borrow);
                found = true;
            }
        }
        if (!found) {
            System.out.println("\nNo borrows processed by you!");
        }
    }

    private void viewStudentHistory() {
        String studentId = inputProcessor.getStringInput("Enter student ID: ");
        Student student = library.findStudentByStudentId(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        boolean found = false;
        System.out.println("Borrow History for " + student.getFirstName() + " " + student.getLastName() + ":");
        for (int i = 0; i < library.getBorrows().size(); i++) {
            Borrow borrow = library.getBorrows().get(i);
            if (borrow.getStudent().getStudentId().equals(studentId)) {
                System.out.println(borrow);
                found = true;
            }
        }
        if (!found) {
            System.out.println("\nNo borrow history for this student.");
        }
    }

    private void changePassword() {
        String currentPass = inputProcessor.getStringInput("Enter current password: ");
        if (!librarian.checkPassword(currentPass)) {
            System.out.println("\nIncorrect current password!");
            return;
        }
        String newPass = inputProcessor.getStringInput("Enter new password: ");
        try {
            librarian.setPassword(newPass);
            System.out.println("\nPassword changed successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("\nPassword change failed: " + e.getMessage());
        }
    }
}