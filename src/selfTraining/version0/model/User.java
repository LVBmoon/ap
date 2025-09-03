package src.selfTraining.version0.model;

public abstract class User {
    private String firstName;
    private String lastName;

    public User(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public abstract String getUsername();

    public abstract String getPassword();

    public abstract boolean checkPassword(String password);
}