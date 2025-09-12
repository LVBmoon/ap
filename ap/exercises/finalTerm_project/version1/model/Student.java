package ap.exercises.finalTerm_project.version1.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Student extends ap.exercises.finalTerm_project.version1.model.User {
    private String studentId;
    private String fieldOfStudy;
    private LocalDate joinDate;
    private String username;
    private String password;
    private ArrayList<ap.exercises.finalTerm_project.version1.model.Book> borrowedBooks;
    private boolean isActive;
    private ap.exercises.finalTerm_project.version1.core.Library library;

    public Student(String firstName, String lastName, String studentId, String fieldOfStudy, String username,
                   String password, ap.exercises.finalTerm_project.version1.core.Library library) {
        super(firstName, lastName);
        setStudentId(studentId);
        setFieldOfStudy(fieldOfStudy);
        setUsername(username);
        setPassword(password);
        this.joinDate = LocalDate.now();
        this.borrowedBooks = new ArrayList<>();
        this.isActive = true;
        this.library = library;
    }

    public void setStudentId(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be empty!");
        }
        this.studentId = studentId.trim();
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        if (fieldOfStudy == null || fieldOfStudy.trim().isEmpty()) {
            throw new IllegalArgumentException("Field of study cannot be empty!");
        }
        this.fieldOfStudy = fieldOfStudy.trim();
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

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public void setJoinDate(LocalDate joinDate) {
        if (joinDate == null || joinDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid join date!");
        }
        this.joinDate = joinDate;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return new ArrayList<>(borrowedBooks);
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public boolean borrowBook(Book book) {
        if (!isActive) {
            return false;
        }
        if (borrowedBooks.size() >= 5) {
            return false;
        }
        if (!book.isAvailable()) {
            return false;
        }
        borrowedBooks.add(book);
        book.setAvailable(false);
        book.incrementBorrowCount();
        return true;
    }

    public boolean returnBook(ap.exercises.finalTerm_project.version1.model.Book book) {
        for (Book b : borrowedBooks) {
            if (b.getBookId().equalsIgnoreCase(book.getBookId())) {
                borrowedBooks.remove(b);
                book.setAvailable(true);
                return true;
            }
        }
        return false;
    }

    public void addBorrowedBookFromStorage(Book book) {
        borrowedBooks.add(book);
    }

    @Override
    public String toString() {
        return "Student: " + getFirstName() + " " + getLastName() + ", ID: " + studentId +
                ", Field: " + fieldOfStudy + ", Active: " + isActive;
    }
}