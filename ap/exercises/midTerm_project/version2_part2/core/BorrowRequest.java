package ap.exercises.midTerm_project.version2_part2.core;

import ap.exercises.midTerm_project.version2_part2.model.Book;
import ap.exercises.midTerm_project.version2_part2.model.Librarian;
import ap.exercises.midTerm_project.version2_part2.model.Student;

import java.time.LocalDate;

public class BorrowRequest {
    private Book book;
    private Student student;
    private Librarian assignedLibrarian;
    private LocalDate requestDate;
    private String status; // These are "PENDING", "APPROVED", "REJECTED"
    private boolean isReturnRequest; // I set true for return, false for borrow

    public BorrowRequest(Book book, Student student, Librarian assignedLibrarian, LocalDate requestDate, boolean isReturnRequest) {
        if (book == null || student == null || assignedLibrarian == null || requestDate == null) {
            throw new IllegalArgumentException("Borrow request parameters cannot be null!");
        }
        this.book = book;
        this.student = student;
        this.assignedLibrarian = assignedLibrarian;
        this.requestDate = requestDate;
        this.status = "PENDING";
        this.isReturnRequest = isReturnRequest;
    }

    // Getters
    public Book getBook() {
        return book;
    }

    public Student getStudent() {
        return student;
    }

    public Librarian getAssignedLibrarian() {
        return assignedLibrarian;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public String getStatus() {
        return status;
    }

    public boolean isReturnRequest() {
        return isReturnRequest;
    }

    public void approve() {
        this.status = "APPROVED";
    }

    public void reject() {
        this.status = "REJECTED";
    }

    public void setStatus(String status) {
        if (status.equals("PENDING") || status.equals("APPROVED") || status.equals("REJECTED")) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid status: " + status);
        }
    }

    @Override
    public String toString() {
        String type = isReturnRequest ? "Return Request" : "Borrow Request";
        // Handle null or invalid student
        String debtInfo = ", Debt: " + (student != null ? student.calculateDebt() : "N/A") + " IRR";
        // Handle null book
        String bookInfo = ", Book: " + (book != null ? book.getTitle() + " by " + book.getAuthor() +
                " (Year: " + book.getPublicationYear() + ", Pages: " + book.getPageCount() + ")" : "N/A");
        // Handle null student
        String studentInfo = (student != null ? student.getFirstName() + " " + student.getLastName() +
                " (" + student.getStudentId() + "), Field: " + student.getFieldOfStudy() : "Unknown Student");
        // Handle null librarian
        String librarianInfo = (assignedLibrarian != null ? assignedLibrarian.getFirstName() + " " +
                assignedLibrarian.getLastName() : "Unknown Librarian");
        return type + " for " + studentInfo + debtInfo + bookInfo +
                ", Assigned to: " + librarianInfo + ", Status: " + status;
    }
}