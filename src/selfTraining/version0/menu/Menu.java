package src.selfTraining.version0.menu;

import src.selfTraining.version0.core.Library;
import src.selfTraining.version0.data.InputProcessor;

public abstract class Menu {
    protected Library library;
    protected InputProcessor inputProcessor;

    public Menu(Library library, InputProcessor inputProcessor) {
        this.library = library;
        this.inputProcessor = inputProcessor;
    }

    public abstract void showMenu();
}
