package ap.exercises.midTerm_project.version2;

import java.io.Serializable;

public class LibraryManager implements Serializable {
    private String firstName;
    private String lastName;
    private String educationLevel;
    private String username;
    private String password;

    public LibraryManager(String firstName, String lastName, String educationLevel,
                          String username, String password) {
        setFirstName(firstName);
        setLastName(lastName);
        setEducationLevel(educationLevel);
        setUsername(username);
        setPassword(password);
    }

    // Setters :)
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty!");
        }
        this.firstName = firstName.trim();
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty!");
        }
        this.lastName = lastName.trim();
    }

    public void setEducationLevel(String educationLevel) {
        if (educationLevel == null || educationLevel.trim().isEmpty()) {
            throw new IllegalArgumentException("Education level cannot be empty!");
        }
        this.educationLevel = educationLevel.trim();
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty() || username.length() < 4) {
            throw new IllegalArgumentException("Username must be at least 4 characters!");
        }
        this.username = username.trim();
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters!");
        }
        this.password = password;
    }

    // Getters :
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEducationLevel() { return educationLevel; }
    public String getUsername() { return username; }
    public boolean checkPassword(String password) { return this.password.equals(password); }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + educationLevel + ") - Username: " + username;
    }
}
