package ap.exercises.finalTerm_project.version1.model;

public class LibraryManager extends User {
    private String educationLevel;
    private String username;
    private String password;

    public LibraryManager(String firstName, String lastName, String educationLevel, String username, String password) {
        super(firstName, lastName);
        setEducationLevel(educationLevel);
        setUsername(username);
        setPassword(password);
    }

    public void setEducationLevel(String educationLevel) {
        if (educationLevel == null || educationLevel.trim().isEmpty()) {
            throw new IllegalArgumentException("Education level cannot be empty!");
        }
        this.educationLevel = educationLevel.trim();
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty!");
        }
        this.username = username.trim();
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters!");
        }
        this.password = password;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return "Manager: " + getFirstName() + " " + getLastName() + ", Education: " + educationLevel;
    }
}