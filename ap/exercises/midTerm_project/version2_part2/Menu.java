package ap.exercises.midTerm_project.version2_part2;

import java.util.Scanner;

public abstract class Menu {
    protected Library library;
    protected Scanner scanner;
    protected InputProcessor inputProcessor;

    public Menu(Library library, Scanner scanner, InputProcessor inputProcessor) {
        this.library = library;
        this.scanner = scanner;
        this.inputProcessor = inputProcessor;
    }

    public abstract void showMenu();
}
