package ap.exercises.finalTerm_project.version2.service;

import ap.exercises.finalTerm_project.version2.core.Library;
import ap.exercises.finalTerm_project.version2.data.InputProcessor;

public abstract class BaseService {
    protected Library library;
    protected InputProcessor inputProcessor;

    public BaseService(Library library, InputProcessor inputProcessor) {
        this.library = library;
        this.inputProcessor = inputProcessor;
    }
}