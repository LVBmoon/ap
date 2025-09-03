package src.selfTraining.version0;

import src.selfTraining.version0.core.Library;
import src.selfTraining.version0.data.DataStorage;
import src.selfTraining.version0.data.InputProcessor;
import src.selfTraining.version0.menu.GuestMenu;
import src.selfTraining.version0.menu.LibrarianMenu;
import src.selfTraining.version0.menu.ManagerMenu;
import src.selfTraining.version0.menu.StudentMenu;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputProcessor inputProcessor = new InputProcessor(scanner);
        DataStorage dataStorage = new DataStorage();
        Library library = dataStorage.loadLibrary();

        StudentMenu studentMenu = new StudentMenu(library, inputProcessor);
        LibrarianMenu librarianMenu = new LibrarianMenu(library, inputProcessor);
        ManagerMenu managerMenu = new ManagerMenu(library, inputProcessor);
        GuestMenu guestMenu = new GuestMenu(library, inputProcessor);

        while (true) {
            System.out.println("\n=== Library System ===");
            System.out.println("   1. Guest Menu");
            System.out.println("   2. Student Menu");
            System.out.println("   3. Librarian Menu");
            System.out.println("   4. Manager Menu");
            System.out.println("   5. Exit");
            int choice = inputProcessor.getIntInput("Please enter choice: ");
            switch (choice) {
                case 1:
                    guestMenu.showMenu();
                    break;
                case 2:
                    studentMenu.showMenu();
                    break;
                case 3:
                    librarianMenu.showMenu();
                    break;
                case 4:
                    managerMenu.showMenu();
                    break;
                case 5:
                    dataStorage.saveLibrary(library);
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}