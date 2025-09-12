package ap.exercises.finalTerm_project.version2.core;
//---
import ap.exercises.finalTerm_project.version2.model.Book;
import ap.exercises.finalTerm_project.version2.model.Librarian;
import ap.exercises.finalTerm_project.version2.model.Student;
//---
import java.time.LocalDate;

public class BorrowRequest {
    private Book book;
    private Student student;
    private Librarian assignedLibrarian;
    private LocalDate requestDate;
    private LocalDate dueDate;
    private RequestStatus status;
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
        this.status = RequestStatus.PENDING;
        this.isReturnRequest = isReturnRequest;
    }

    public void approve() {
        this.status = RequestStatus.APPROVED;
    }

    public void reject() {
        this.status = RequestStatus.REJECTED;
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

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
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