package ap.exercises.midTerm_project.version2_part2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DataStorage storage = new DataStorage();
        Library library = storage.loadLibrary();
        Scanner scanner = new Scanner(System.in);
        InputProcessor inputProcessor = new InputProcessor(scanner);

        while (true) {
            System.out.println("\n*** University Library Management System ***");
            System.out.println("   1. Student");
            System.out.println("   2. Librarian");
            System.out.println("   3. Manager");
            System.out.println("   4. Exit");
            int choice = inputProcessor.getIntInput("Please enter choice: ");

            Menu menu;
            switch (choice) {
                case 1:
                    menu = new StudentMenu(library, inputProcessor);
                    menu.showMenu();
                    break;
                case 2:
                    menu = new LibrarianMenu(library,inputProcessor);
                    menu.showMenu();
                    break;
                case 3:
                    menu = new ManagerMenu(library, inputProcessor);
                    menu.showMenu();
                    break;
                case 4:
                    storage.saveLibrary(library);
                    System.out.println("\nData saved. Exiting...\nGood Bye! :)");
                    scanner.close();
                    return;
                default:
                    System.out.println("\nInvalid choice.");
            }
            storage.saveLibrary(library);
        }
    }
}