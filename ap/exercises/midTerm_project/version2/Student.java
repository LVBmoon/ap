package ap.exercises.midTerm_project.version2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Student implements Serializable {
    private String firstName;
    private String lastName;
    private String studentId;
    private String fieldOfStudy;
    private Date joinDate;
    private String username;
    private String password;
    private ArrayList<Book> borrowedBooks;

    public Student(String firstName, String lastName, String studentId, String fieldOfStudy,
                   Date joinDate, String username, String password) {
        setFirstName(firstName);
        setLastName(lastName);
        setStudentId(studentId);
        setFieldOfStudy(fieldOfStudy);
        setJoinDate(joinDate);
        setUsername(username);
        setPassword(password);
        this.borrowedBooks = new ArrayList<>();
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

    public void setStudentId(String studentId) {
        if (studentId != null && studentId.length() == 9){
            this.studentId = studentId.trim();
        }
        throw new IllegalArgumentException("Student ID cannot be empty or more and less than 9 characters!");
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        if (fieldOfStudy == null || fieldOfStudy.trim().isEmpty()) {
            throw new IllegalArgumentException("Field of study cannot be empty!");
        }
        this.fieldOfStudy = fieldOfStudy.trim();
    }

    public void setJoinDate(Date joinDate) {
        if (joinDate == null || joinDate.after(new Date())) {
            throw new IllegalArgumentException("Invalid join date!");
        }
        this.joinDate = joinDate;
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

    // Book borrowing methods :
    public boolean borrowBook(Book book) {
        if (borrowedBooks.size() >= 5) {
            return false;
        }
        if (book.isAvailable()) {
            borrowedBooks.add(book);
            book.setAvailable(false);
            return true;
        }
        return false;
    }

    public boolean returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.setAvailable(true);
            return true;
        }
        return false;
    }

    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getStudentId() { return studentId; }
    public String getFieldOfStudy() { return fieldOfStudy; }
    public Date getJoinDate() { return joinDate; }
    public String getUsername() { return username; }
    public boolean checkPassword(String password) { return this.password.equals(password); }
    public ArrayList<Book> getBorrowedBooks() { return new ArrayList<>(borrowedBooks); }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + studentId + ") - " + fieldOfStudy +
                " - Joined: " + joinDate + " - Username: " + username;
    }
}
