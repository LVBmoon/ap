package src.selfTraining.version0.core;

import src.selfTraining.version0.model.Book;
import src.selfTraining.version0.model.Librarian;
import src.selfTraining.version0.model.Student;
import java.time.LocalDate;

public class BorrowRequest {
    private Book book;
    private Student student;
    private Librarian assignedLibrarian;
    private LocalDate requestDate;
    private String status;
    private boolean isReturnRequest;

    public BorrowRequest(Book book, Student student, Librarian assignedLibrarian, LocalDate requestDate, boolean isReturnRequest) {
        this.book = book;
        this.student = student;
        this.assignedLibrarian = assignedLibrarian;
        this.requestDate = requestDate;
        this.status = "PENDING";
        this.isReturnRequest = isReturnRequest;
    }

    public void approve() {
        this.status = "APPROVED";
    }

    public void reject() {
        this.status = "REJECTED";
    }

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

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isReturnRequest() {
        return isReturnRequest;
    }

    @Override
    public String toString() {
        String requestType = isReturnRequest ? "Return" : "Borrow";
        return requestType + " Request: " + book.getTitle() + " by " + student.getFirstName() + " " +
                student.getLastName() + ", Assigned to: " + assignedLibrarian.getFirstName() + " " +
                assignedLibrarian.getLastName() + ", Status: " + status + ", Date: " + requestDate;
    }
}