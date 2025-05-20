package ap.exercises.midTerm_project.version2_part2;

public abstract class Menu {
    protected Library library;
    protected InputProcessor inputProcessor;

    public Menu(Library library, InputProcessor inputProcessor) {
        this.library = library;
        this.inputProcessor = inputProcessor;
    }

    public abstract void showMenu();
}
