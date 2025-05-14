package ap.exercises.midTerm_project.version2_part1;

import java.io.Serializable;

public class Librarian implements Serializable {
    private String firstName;
    private String lastName;
    private String employeeId;
    private String username;
    private String password;
    private int processedLoans;

    public Librarian(String firstName, String lastName, String employeeId,
                     String username, String password) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmployeeId(employeeId);
        setUsername(username);
        setPassword(password);
        this.processedLoans = 0;
    }

    // Setters :
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

    public void setEmployeeId(String employeeId) {
        if (employeeId == null || employeeId.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee ID cannot be empty!");
        }
        this.employeeId = employeeId.trim();
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

    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmployeeId() { return employeeId; }
    public String getUsername() { return username; }
    public boolean checkPassword(String password) { return this.password.equals(password); }
    public int getProcessedLoans() { return processedLoans; }
    public void incrementProcessedLoans() { processedLoans++; }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + employeeId + ") - Username: " + username +
                " - Processed loans: " + processedLoans;
    }
}
