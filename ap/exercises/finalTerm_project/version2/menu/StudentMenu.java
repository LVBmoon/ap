package ap.exercises.finalTerm_project.version2.menu;

import ap.exercises.finalTerm_project.version2.core.Library;
import ap.exercises.finalTerm_project.version2.data.DataStorage;
import ap.exercises.finalTerm_project.version2.data.InputProcessor;
import ap.exercises.finalTerm_project.version2.service.StudentService;

public class StudentMenu extends Menu {
    private StudentService studentService;

    public StudentMenu(Library library, InputProcessor inputProcessor) {
        super(library, inputProcessor);
        this.studentService = new StudentService(library, inputProcessor);
    }

    @Override
    public void showMenu() {
        while (true) {
            if (!studentService.isLoggedIn()) {
                System.out.println("\n=== Student Menu ===");
                System.out.println("   1. Register");
                System.out.println("   2. Login");
                System.out.println("   3. Exit");
                int choice = inputProcessor.getIntInput("Please enter choice: ");
                switch (choice) {
                    case 1:
                        studentService.register();
                        break;
                    case 2:
                        studentService.login();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } else {
                System.out.println("\n--- Student Menu ---\nWelcome " + studentService.getStudent().getFirstName() + " " + studentService.getStudent().getLastName() + "!");
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
                        studentService.searchBooks();
                        break;
                    case 2:
                        studentService.requestBorrow();
                        break;
                    case 3:
                        studentService.viewActiveBorrows();
                        break;
                    case 4:
                        studentService.requestReturn();
                        break;
                    case 5:
                        studentService.viewBorrowHistory();
                        break;
                    case 6:
                        studentService.viewRequestStatus();
                        break;
                    case 7:
                        studentService.changePassword();
                        break;
                    case 8:
                        studentService = new StudentService(library, inputProcessor); // reset logout
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
            new DataStorage().saveLibrary(library);
        }
    }
}