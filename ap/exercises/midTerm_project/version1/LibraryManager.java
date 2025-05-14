package ap.exercises.midTerm_project.version1;

import java.io.Serializable;

public class LibraryManager implements Serializable {
    private String firstName;
    private String lastName;
    private String educationLevel;

    public LibraryManager(String firstName, String lastName, String educationLevel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.educationLevel = educationLevel;
    }

    // Getters - I didn't write setters in this version
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEducationLevel() { return educationLevel; }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + educationLevel + ")";
    }
}
