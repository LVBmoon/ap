package ap.exercises.midTerm_project.version2_part1;

public class Main {
    public static void main(String[] args) {
        // This try to load library from file
        Library library = Library.loadFromFile("library_data.ser");

        if (library == null) {
            // If file doesn't exist, create new library :)
            System.out.println("Creating new library...");
            LibraryManager manager = new LibraryManager("Library", "Manager", "PhD", "admin", "admin123");
            library = new Library("University Central Library", manager);

            // Add initial librarians as wanted
            library.addLibrarian(new Librarian("LVB", "moon", "L001", "LVB!", "codingIsFun88"));
            library.addLibrarian(new Librarian("Dervish", "Grady", "L002", "TheGreatMoreed", "deathIsNotTheEnding"));
        }

        Menu menu = new Menu(library);
        menu.showMainMenu();
    }
}
