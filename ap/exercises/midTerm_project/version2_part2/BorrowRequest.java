package ap.exercises.midTerm_project.version2_part2;

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
        String debtInfo = ", Debt: " + student.calculateDebt() + " IRR";
        String bookInfo = ", Book: " + book.getTitle() + " by " + book.getAuthor() +
                " (Year: " + book.getPublicationYear() + ", Pages: " + book.getPageCount() + ")";
        String studentInfo = student.getFirstName() + " " + student.getLastName() +
                " (" + student.getStudentId() + "), Field: " + student.getFieldOfStudy();
        return type + " for " + studentInfo + debtInfo + bookInfo +
                ", Assigned to: " + assignedLibrarian.getFirstName() + " " +
                assignedLibrarian.getLastName() + ", Status: " + status;
    }
}