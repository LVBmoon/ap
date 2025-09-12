package ap.exercises.finalTerm_project.version2.menu;

import ap.exercises.finalTerm_project.version2.core.Library;
import ap.exercises.finalTerm_project.version2.data.DataStorage;
import ap.exercises.finalTerm_project.version2.data.InputProcessor;
import ap.exercises.finalTerm_project.version2.service.ManagerService;

public class ManagerMenu extends Menu {
    private ManagerService managerService;

    public ManagerMenu(Library library, InputProcessor inputProcessor) {
        super(library, inputProcessor);
        this.managerService = new ManagerService(library, inputProcessor);
    }

    @Override
    public void showMenu() {
        while (true) {
            if (!managerService.isLoggedIn()) {
                System.out.println("\n=== Manager Menu ===");
                System.out.println("   1. Login");
                System.out.println("   2. Exit");
                int choice = inputProcessor.getIntInput("Please enter choice: ");
                switch (choice) {
                    case 1:
                        managerService.login();
                        break;
                    case 2:
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } else {
                System.out.println("\n--- Manager Menu ---\nWelcome " + managerService.getManager().getFirstName() + " " + managerService.getManager().getLastName() + "!");
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
                        managerService.addNewLibrarian();
                        break;
                    case 2:
                        managerService.viewOverdueBorrows();
                        break;
                    case 3:
                        managerService.viewLibrarianStatistics();
                        break;
                    case 4:
                        managerService.viewMostBorrowedBooks();
                        break;
                    case 5:
                        managerService.viewBorrowStatistics();
                        break;
                    case 6:
                        managerService.viewTopDelayedStudents();
                        break;
                    case 7:
                        managerService.changePassword();
                        break;
                    case 8:
                        managerService = new ManagerService(library, inputProcessor); // reset logout
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
            new DataStorage().saveLibrary(library);
        }
    }
}