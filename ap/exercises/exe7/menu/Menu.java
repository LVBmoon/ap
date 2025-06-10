package ap.exercises.exe7.menu;

import ap.exercises.midTerm_project.version2_part2.core.Library;
import ap.exercises.midTerm_project.version2_part2.data.InputProcessor;

public abstract class Menu {
    protected Library library;
    protected InputProcessor inputProcessor;

    public Menu(Library library, InputProcessor inputProcessor) {
        this.library = library;
        this.inputProcessor = inputProcessor;
    }

    public abstract void showMenu();
}
