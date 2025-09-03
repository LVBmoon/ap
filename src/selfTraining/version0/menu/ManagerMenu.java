package src.selfTraining.version0.menu;

import src.selfTraining.version0.core.Borrow;
import src.selfTraining.version0.core.Library;
import src.selfTraining.version0.data.DataStorage;
import src.selfTraining.version0.data.InputProcessor;
import src.selfTraining.version0.model.Book;
import src.selfTraining.version0.model.Librarian;
import src.selfTraining.version0.model.LibraryManager;
import src.selfTraining.version0.model.Student;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ManagerMenu extends Menu {
    private LibraryManager manager;

    public ManagerMenu(Library library, InputProcessor inputProcessor) {
        super(library, inputProcessor);
        this.manager = null;
    }

    @Override
    public void showMenu() {
        while (true) {
            if (manager == null) {
                System.out.println("\n=== Manager Menu ===");
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
                        System.out.println("Invalid choice. Try again.");
                }
            } else {
                System.out.println("\n--- Manager Menu ---\nWelcome " + manager.getFirstName() + " " + manager.getLastName() + "!");
                System.out.println("   1. Add New Librarian");
                System.out.println("   2. View Overdue Borrows");
                System.out.println("   3. View Librarian Statistics");
                System.out.println("   4. View Most Borrowed Books");
                System.out.println("   5. View Borrow Statistics");
                System.out.println("   6. View Top Delayed Students");
                System.out.println("   7. Change Password");
                System.out.println("   8. Logout");
                int choice = inputProcessor.getIntInput("Please enter choice: ");
                switch (choice) {
                    case 1:
                        addNewLibrarian();
                        break;
                    case 2:
                        viewOverdueBorrows();
                        break;
                    case 3:
                        viewLibrarianStatistics();
                        break;
                    case 4:
                        viewMostBorrowedBooks();
                        break;
                    case 5:
                        viewBorrowStatistics();
                        break;
                    case 6:
                        viewTopDelayedStudents();
                        break;
                    case 7:
                        changePassword();
                        break;
                    case 8:
                        manager = null;
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
            new DataStorage().saveLibrary(library);
        }
    }

    private void login() {
        String username = inputProcessor.getStringInput("Enter username: ");
        String password = inputProcessor.getStringInput("Enter password: ");
        LibraryManager m = library.getManager();
        if (m.getUsername().equals(username) && m.checkPassword(password)) {
            manager = m;
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid username or password!");
        }
    }

    private void addNewLibrarian() {
        String firstName = inputProcessor.getStringInput("Enter librarian first name: ");
        String lastName = inputProcessor.getStringInput("Enter librarian last name: ");
        String employeeId = inputProcessor.getStringInput("Enter employee ID: ");
        String username = inputProcessor.getStringInput("Enter username: ");
        String password = inputProcessor.getStringInput("Enter password: ");
        String educationLevel = inputProcessor.getStringInput("Enter education level (e.g., BSc): ");
        Librarian existing = library.findLibrarianByEmployeeId(employeeId);
        if (existing != null) {
            System.out.println("Employee ID already exists!");
            return;
        }
        for (Librarian l : library.getLibrarians()) {
            if (l.getUsername().equals(username)) {
                System.out.println("Username already exists!");
                return;
            }
        }
        try {
            Librarian librarian = new Librarian(firstName, lastName, employeeId, username, password, educationLevel);
            library.addLibrarian(librarian);
            System.out.println("Librarian added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to add librarian: " + e.getMessage());
        }
    }

    private void viewOverdueBorrows() {
        ArrayList<Borrow> overdue = library.getOverdueBorrows();
        if (overdue.isEmpty()) {
            System.out.println("No overdue borrows.");
        } else {
            System.out.println("Overdue Borrows:");
            for (Borrow borrow : overdue) {
                System.out.println(borrow);
            }
        }
    }

    private void viewLibrarianStatistics() {
        System.out.println("Librarian Statistics:");
        for (Librarian librarian : library.getLibrarians()) {
            System.out.println(librarian.getFirstName() + " " + librarian.getLastName() +
                    ": " + librarian.getProcessedLoans() + " borrows processed");
        }
    }

    private void viewMostBorrowedBooks() {
        ArrayList<Book> topBooks = library.getMostBorrowedBooks(10);
        if (topBooks.isEmpty()) {
            System.out.println("No books borrowed yet!");
        } else {
            System.out.println("Top 10 Most Borrowed Books:");
            for (Book book : topBooks) {
                System.out.println(book + ", Borrow Count: " + book.getBorrowCount());
            }
        }
    }

    private void viewBorrowStatistics() {
        System.out.println("Borrow Statistics:");
        System.out.println("Total Borrow Requests: " + library.getTotalBorrowRequests());
        System.out.println("Total Approved Borrows: " + library.getTotalApprovedBorrows());
        System.out.println("Average Borrow Days: " + String.format("%.2f", library.getAverageBorrowDays()));
    }

    private void viewTopDelayedStudents() {
        ArrayList<Student> topDelayed = library.getTopDelayedStudents(10);
        if (topDelayed.isEmpty()) {
            System.out.println("No delayed borrows found.");
        } else {
            System.out.println("Top 10 Students with Most Delay Days:");
            for (Student student : topDelayed) {
                long totalDelay = 0;
                for (Borrow borrow : library.getBorrows()) {
                    if (borrow.getStudent().getStudentId().equals(student.getStudentId()) && borrow.isOverdue()) {
                        LocalDate endDate = borrow.isReturned() ? borrow.getReturnDate() : LocalDate.now();
                        totalDelay += ChronoUnit.DAYS.between(borrow.getDueDate(), endDate);
                    }
                }
                System.out.println(student.getFirstName() + " " + student.getLastName() +
                        " (" + student.getStudentId() + ") - Total Delay Days: " + totalDelay);
            }
        }
    }

    private void changePassword() {
        String currentPass = inputProcessor.getStringInput("Enter current password: ");
        if (!manager.checkPassword(currentPass)) {
            System.out.println("Incorrect current password!");
            return;
        }
        String newPass = inputProcessor.getStringInput("Enter new password: ");
        try {
            manager.setPassword(newPass);
            System.out.println("Password changed successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Password change failed: " + e.getMessage());
        }
    }
}