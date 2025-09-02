package ap.exercises.finalTerm_project.version1.menu;

import ap.exercises.finalTerm_project.version1.core.Library;
import ap.exercises.finalTerm_project.version1.data.InputProcessor;

public abstract class Menu {
    protected Library library;
    protected InputProcessor inputProcessor;

    public Menu(Library library, InputProcessor inputProcessor) {
        this.library = library;
        this.inputProcessor = inputProcessor;
    }

    public abstract void showMenu();
}