package ap.exercises.midTerm_project.version1;

import java.io.Serializable;

public class Librarian implements Serializable {
    private String firstName;
    private String lastName;
    private String employeeId;
    private int booksProcessed;

    public Librarian(String firstName, String lastName, String employeeId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeId = employeeId;
        this.booksProcessed = 0;
    }

    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmployeeId() { return employeeId; }
    public int getBooksProcessed() { return booksProcessed; }

    public void incrementProcessedBooks() {
        booksProcessed++;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (Employee ID: " + employeeId + ")";
    }
}
