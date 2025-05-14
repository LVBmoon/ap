package ap.exercises.Main_midTerm_project.version1;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {
    private String firstName;
    private String lastName;
    private String studentId;
    private String major;
    private LocalDate registrationDate;
    private List<Book> borrowedBooks;

    public Student(String firstName, String lastName, String studentId, String major) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
        this.major = major;
        this.registrationDate = LocalDate.now();
        this.borrowedBooks = new ArrayList<>();
    }

    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }

    public String getStudentId() { return studentId; }
    public void setStudentID(int studentID) {
        if (String.valueOf(studentID).length() == 9){
            this.studentId = String.valueOf(studentID);
        }
        else {
            throw new IllegalArgumentException("Student ID must be a 9-digit number!");
        }
    }

    public String getMajor() { return major; }
    public void setMajor(String major) {
        if (major != null && major.length() > 2) {
            this.major = major;
        }
        else {
            throw new IllegalArgumentException("Major cannot be empty or less than 3 characters!");
        }
    }

    public LocalDate getRegistrationDate() { return registrationDate; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }



    public boolean borrowBook(Book book) {
        if (book.isAvailable()) {
            borrowedBooks.add(book);
            book.setAvailable(false);
            return true;
        }
        return false;
    }

    public boolean returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.setAvailable(true);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (ID: " + studentId + ") - " + major;
    }
}
