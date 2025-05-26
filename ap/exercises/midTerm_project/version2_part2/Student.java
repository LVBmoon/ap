package ap.exercises.midTerm_project.version2_part2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;

public class Student extends User {
    private String studentId;
    private String fieldOfStudy;
    private LocalDate joinDate;
    private String username;
    private String password;
    private ArrayList<Book> borrowedBooks;
    private Library library; // Added to access library borrows for debt calculation

    public Student(String firstName, String lastName, String studentId, String fieldOfStudy, String username, String password, Library library) {
        super(firstName, lastName);
        setStudentId(studentId);
        setFieldOfStudy(fieldOfStudy);
        setUsername(username);
        setPassword(password);
        this.joinDate = LocalDate.now();
        this.borrowedBooks = new ArrayList<>();
        this.library = library; // Set library reference
    }

    // Setters
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

    // Book borrowing methods
    public boolean borrowBook(Book book) {
        if (borrowedBooks.size() >= 5) {
            return false;
        }
        if (book.isAvailable()) {
            borrowedBooks.add(book);
            book.setAvailable(false);
            book.incrementBorrowCount();
            return true;
        }
        return false;
    }

    public boolean returnBook(Book book) {
        for (int i = 0; i < borrowedBooks.size(); i++) {
            if (borrowedBooks.get(i) == book) {
                borrowedBooks.remove(i);
                book.setAvailable(true);
                return true;
            }
        }
        return false;
    }

    // Calculate debt (500 IRR per day overdue)
    public long calculateDebt() {
        long totalDebt = 0;
        // Check if library is available to avoid errors
        if (library == null) {
            System.out.println("Error: Library reference is missing!");
            return 0;
        }
        for (Borrow borrow : library.getBorrows()) {
            if (borrow.getStudent().getStudentId().equals(this.studentId) && borrow.isOverdue()) {
                LocalDate endDate = borrow.isReturned() ? borrow.getReturnDate() : LocalDate.now();
                long daysOverdue = ChronoUnit.DAYS.between(borrow.getDueDate(), endDate);
                if (daysOverdue > 0) {
                    totalDebt += daysOverdue * 500;
                }
            }
        }
        return totalDebt;
    }

    // Getters
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

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public ArrayList<Book> getBorrowedBooks() {
        return new ArrayList<>(borrowedBooks);
    }

    public String getPassword() {
        return password; // Keep plaintext as requested
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + studentId + ") - " + fieldOfStudy +
                " - Joined: " + joinDate + " - Username: " + username;
    }
}