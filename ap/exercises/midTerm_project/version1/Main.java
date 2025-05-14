package ap.exercises.midTerm_project.version1;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = initializeLibrary();

        // Try to load from file if it cant it will make a new one
        try {
            Library loadedLibrary = Library.loadFromFile("library.dat");
            if (loadedLibrary != null) {
                library = loadedLibrary;
                System.out.println("The Library information loaded successfully.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Couldn't find the library.Making new one...");
        }

        LibraryMenu menu = new LibraryMenu(library);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            menu.showMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    menu.handleStudentOperations();
                    break;
                case 2:
                    menu.handleManagerOperations();
                    break;
                case 3:
                    try {
                        library.saveToFile("library.dat");
                        System.out.println("Library information saved successfully.\n");
                    } catch (IOException e) {
                        System.out.println("error in saving information!");
                    }
                    System.out.println("thank you for using program!...Good Bye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("invalid choice!");
            }
        }
    }

    private static Library initializeLibrary() {
        // Create manager
        LibraryManager manager = new LibraryManager("admin", "library", "PHD");

        // Create library
        Library library = new Library("Central University Library", manager);

        // Add 2 librarians
        library.addLibrarian(new Librarian("librarian", "first", "LIB001"));
        library.addLibrarian(new Librarian("librarian", "second", "LIB002"));

        return library;
    }
}
