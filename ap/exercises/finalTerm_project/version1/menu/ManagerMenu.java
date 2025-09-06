package ap.exercises.finalTerm_project.version1.menu;

import ap.exercises.finalTerm_project.version1.core.Library;
import ap.exercises.finalTerm_project.version1.data.DataStorage;
import ap.exercises.finalTerm_project.version1.data.InputProcessor;
import ap.exercises.finalTerm_project.version1.model.Librarian;
import ap.exercises.finalTerm_project.version1.model.LibraryManager;

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
                        System.out.println("NOT IMPLEMENTED");
                        break;
                    case 3:
                        viewLibrarianStatistics();
                        break;
                    case 4:
                        System.out.println("NOT IMPLEMENTED");
                        break;
                    case 5:
                        System.out.println("NOT IMPLEMENTED");
                        break;
                    case 6:
                        System.out.println("NOT IMPLEMENTED");
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

    private void viewLibrarianStatistics() {
        System.out.println("Librarian Statistics:");
        for (Librarian librarian : library.getLibrarians()) {
            System.out.println(librarian.getFirstName() + " " + librarian.getLastName() +
                    ": Registered Books: " + librarian.getRegisteredBooks() +
                    ", Borrows Given: " + librarian.getBorrowsGiven() +
                    ", Borrows Received: " + librarian.getBorrowsReceived() +
                    ", Total Processed: " + librarian.getProcessedLoans());
        }
    }
}
