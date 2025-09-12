package ap.exercises.finalTerm_project.version2.core;
//---
import ap.exercises.finalTerm_project.version2.model.Book;
import ap.exercises.finalTerm_project.version2.model.Librarian;
import ap.exercises.finalTerm_project.version2.model.Student;
//---
import java.time.LocalDate;

public class Borrow {
    private Book book;
    private Student student;
    private Librarian issuedBy;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private boolean isReturned;
    private Librarian receivedBy;
    private LocalDate returnDate;

    public Borrow(Book book, Student student, Librarian issuedBy, LocalDate borrowDate) {
        this.book = book;
        this.student = student;
        this.issuedBy = issuedBy;
        this.borrowDate = borrowDate;
        this.dueDate = borrowDate.plusDays(14);
        this.isReturned = false;
        this.receivedBy = null;
        this.returnDate = null;
    }

    public void returnBook(Librarian librarian, LocalDate returnDate) {
        this.isReturned = true;
        this.receivedBy = librarian;
        this.returnDate = returnDate;
    }

    public boolean isOverdue() {
        if (isReturned) {
            return returnDate.isAfter(dueDate);
        }
        return LocalDate.now().isAfter(dueDate);
    }

    public Book getBook() {
        return book;
    }

    public Student getStudent() {
        return student;
    }

    public Librarian getIssuedBy() {
        return issuedBy;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public Librarian getReceivedBy() {
        return receivedBy;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    @Override
    public String toString() {
        String returnInfo = isReturned ?
                ", Returned to: " + receivedBy.getFirstName() + " " + receivedBy.getLastName() +
                        ", Return Date: " + returnDate :
                "";
        return "Borrow: " + book.getTitle() + " (ID: " + book.getBookId() + ") by " +
                student.getFirstName() + " " + student.getLastName() +
                ", Issued by: " + issuedBy.getFirstName() + " " + issuedBy.getLastName() +
                ", Borrow Date: " + borrowDate + ", Due Date: " + dueDate + returnInfo;
    }
}