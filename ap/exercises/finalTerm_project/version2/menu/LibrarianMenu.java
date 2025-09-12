package ap.exercises.finalTerm_project.version2.menu;

import ap.exercises.finalTerm_project.version2.core.Library;
import ap.exercises.finalTerm_project.version2.data.DataStorage;
import ap.exercises.finalTerm_project.version2.data.InputProcessor;
import ap.exercises.finalTerm_project.version2.service.LibrarianService;

public class LibrarianMenu extends Menu {
    private LibrarianService librarianService;

    public LibrarianMenu(Library library, InputProcessor inputProcessor) {
        super(library, inputProcessor);
        this.librarianService = new LibrarianService(library, inputProcessor);
    }

    @Override
    public void showMenu() {
        while (true) {
            if (!librarianService.isLoggedIn()) {
                System.out.println("\n=== Librarian Menu ===");
                System.out.println("   1. Login");
                System.out.println("   2. Exit");
                int choice = inputProcessor.getIntInput("Please enter choice: ");
                switch (choice) {
                    case 1:
                        librarianService.login();
                        break;
                    case 2:
                        return;
                    default:
                        System.out.println("\nInvalid choice!");
                }
            } else {
                System.out.println("\n--- Librarian Menu ---\nWelcome " + librarianService.getLibrarian().getFirstName() + " " + librarianService.getLibrarian().getLastName() + "!");
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
                    case 1:
                        librarianService.editPersonalInfo();
                        break;
                    case 2:
                        librarianService.addNewBook();
                        break;
                    case 3:
                        librarianService.editBook();
                        break;
                    case 4:
                        librarianService.reviewBorrowRequests();
                        break;
                    case 5:
                        librarianService.reviewReturnRequests();
                        break;
                    case 6:
                        librarianService.viewProcessedBorrows();
                        break;
                    case 7:
                        librarianService.viewStudentHistory();
                        break;
                    case 8:
                        librarianService.changePassword();
                        break;
                    case 9:
                        librarianService.toggleStudentActive();
                        break;
                    case 10:
                        librarianService = new LibrarianService(library, inputProcessor); // reset to logout
                        break;
                    default:
                        System.out.println("\nInvalid choice!");
                }
            }
            new DataStorage().saveLibrary(library);
        }
    }
}