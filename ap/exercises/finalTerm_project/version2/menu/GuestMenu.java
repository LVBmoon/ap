package ap.exercises.finalTerm_project.version2.menu;

import ap.exercises.finalTerm_project.version2.service.GuestService;
import ap.exercises.finalTerm_project.version2.core.Library;
import ap.exercises.finalTerm_project.version2.data.DataStorage;
import ap.exercises.finalTerm_project.version2.data.InputProcessor;

public class GuestMenu extends Menu {
    private GuestService guestService;

    public GuestMenu(Library library, InputProcessor inputProcessor) {
        super(library, inputProcessor);
        this.guestService = new GuestService(library, inputProcessor);
    }

    @Override
    public void showMenu() {
        while (true) {
            System.out.println("\n=== Guest Menu ===");
            System.out.println("   1. View Total Students");
            System.out.println("   2. Search Books by Title");
            System.out.println("   3. View Library Statistics");
            System.out.println("   4. Exit");
            int choice = inputProcessor.getIntInput("Please enter choice: ");
            switch (choice) {
                case 1:
                    guestService.viewTotalStudents();
                    break;
                case 2:
                    guestService.searchBooks();
                    break;
                case 3:
                    guestService.viewStatistics();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
            new DataStorage().saveLibrary(library);
        }
    }
}