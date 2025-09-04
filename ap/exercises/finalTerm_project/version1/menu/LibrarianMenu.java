package ap.exercises.finalTerm_project.version1.menu;

import src.selfTraining.version0.core.Library;
import src.selfTraining.version0.data.DataStorage;
import src.selfTraining.version0.data.InputProcessor;
import src.selfTraining.version0.menu.Menu;
import src.selfTraining.version0.model.Librarian;

public class LibrarianMenu extends Menu {
    private Librarian librarian;

    public LibrarianMenu(Library library, InputProcessor inputProcessor) {
        super(library, inputProcessor);
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
                        System.out.println("\nInvalid choice!");
                }
            } else {
                System.out.println("\n--- Librarian Menu ---\nWelcome " + librarian.getFirstName() + " " + librarian.getLastName() + "!");
                System.out.println("   1. Edit Personal Info");
                System.out.println("   2. Add New Book");
                System.out.println("   3. Edit Book");
                System.out.println("   4. Review Borrow Requests");
                System.out.println("   5. Review Return Requests");
                System.out.println("   6. View Processed Borrows");
                System.out.println("   7. View Student Borrow History");
                System.out.println("   8. Change Password");
                System.out.println("   9. Toggle Student Active Status");
                System.out.println("   10. Logout");
                int choice = inputProcessor.getIntInput("Please enter choice: ");
                switch (choice) {
                    case 8:
                        changePassword();

                    default:
                        System.out.println("\nInvalid choice!");
                }
            }
            new DataStorage().saveLibrary(library);
        }
    }

    private void login() {
        String username = inputProcessor.getStringInput("Enter username: ");
        String password = inputProcessor.getStringInput("Enter password: ");
        for (Librarian l : library.getLibrarians()) {
            if (l.getUsername().equals(username) && l.checkPassword(password)) {
                librarian = l;
                System.out.println("\nLogin successful.");
                return;
            }
        }
        System.out.println("\nInvalid username or password!");
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

