package ap.exercises.finalTerm_project.version1.core;

import ap.exercises.finalTerm_project.version1.model.Book;
import ap.exercises.finalTerm_project.version1.model.Librarian;
import ap.exercises.finalTerm_project.version1.model.Student;
import java.time.LocalDate;

public class BorrowRequest {
    private Book book;
    private Student student;
    private Librarian assignedLibrarian;
    private LocalDate requestDate;
    private LocalDate dueDate;
    private ap.exercises.finalTerm_project.version1.core.BorrowRequest.RequestStatus status;
    private boolean isReturnRequest;

    public enum RequestStatus {
        PENDING, APPROVED, REJECTED
    }

    public BorrowRequest(Book book, Student student, Librarian assignedLibrarian, LocalDate requestDate, LocalDate dueDate, boolean isReturnRequest) {
        this.book = book;
        this.student = student;
        this.assignedLibrarian = assignedLibrarian;
        this.requestDate = requestDate;
        this.dueDate = dueDate;
        this.status = ap.exercises.finalTerm_project.version1.core.BorrowRequest.RequestStatus.PENDING;
        this.isReturnRequest = isReturnRequest;
    }

    public void approve() {
        this.status = ap.exercises.finalTerm_project.version1.core.BorrowRequest.RequestStatus.APPROVED;
    }

    public void reject() {
        this.status = ap.exercises.finalTerm_project.version1.core.BorrowRequest.RequestStatus.REJECTED;
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public ap.exercises.finalTerm_project.version1.core.BorrowRequest.RequestStatus getStatus() {
        return status;
    }

    public void setStatus(ap.exercises.finalTerm_project.version1.core.BorrowRequest.RequestStatus status) {
        this.status = status;
    }

    public boolean isReturnRequest() {
        return isReturnRequest;
    }

    @Override
    public String toString() {
        String requestType = isReturnRequest ? "Return" : "Borrow";
        return requestType + " Request: " + book.getTitle() + " (ID: " + book.getBookId() + ") by " +
                student.getFirstName() + " " + student.getLastName() +
                ", Assigned to: " + assignedLibrarian.getFirstName() + " " + assignedLibrarian.getLastName() +
                ", Status: " + status + ", Date: " + requestDate + ", Due Date: " + dueDate;
    }
}