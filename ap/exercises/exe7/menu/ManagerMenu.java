package ap.exercises.exe7.menu;

import ap.exercises.midTerm_project.version2_part2.core.Borrow;
import ap.exercises.midTerm_project.version2_part2.core.Library;
import ap.exercises.midTerm_project.version2_part2.data.InputProcessor;
import ap.exercises.midTerm_project.version2_part2.model.Book;
import ap.exercises.midTerm_project.version2_part2.model.Librarian;
import ap.exercises.midTerm_project.version2_part2.model.LibraryManager;

import java.util.ArrayList;

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
                        System.out.println("Invalid choice!Try again.");
                }
            } else {
                System.out.println("\n--- Manager Menu ---\nWelcome" + manager.getFirstName() + " " + manager.getLastName() + " !");
                System.out.println("   1. Add New Librarian");
                System.out.println("   2. View Overdue Borrows");
                System.out.println("   3. View Librarian Statistics");
                System.out.println("   4. View Most Borrowed Books");
                System.out.println("   5. Change Password");
                System.out.println("   6. Logout");
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
                        changePassword();
                        break;
                    case 6:
                        manager = null;
                        break;
                    default:
                        System.out.println("Invalid choice.Try again.");
                }
            }
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
        String educationLevel = inputProcessor.getStringInput("Enter education level (e.g., BCs): ");
        Librarian existing = library.findLibrarianByEmployeeId(employeeId);
        if (existing != null) {
            System.out.println("Employee ID already exists!");
            return;
        }
        for (int i = 0; i < library.getLibrarians().size(); i++) {
            if (library.getLibrarians().get(i).getUsername().equals(username)) {
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
            for (int i = 0; i < overdue.size(); i++) {
                System.out.println(overdue.get(i));
            }
        }
    }

    private void viewLibrarianStatistics() {
        System.out.println("Librarian Statistics:");
        for (int i = 0; i < library.getLibrarians().size(); i++) {
            Librarian librarian = library.getLibrarians().get(i);
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
            for (int i = 0; i < topBooks.size(); i++) {
                Book book = topBooks.get(i);
                System.out.println(book + ", Borrow Count: " + book.getBorrowCount());
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