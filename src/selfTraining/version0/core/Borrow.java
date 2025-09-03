package src.selfTraining.version0.core;

import src.selfTraining.version0.model.Book;
import src.selfTraining.version0.model.Librarian;
import src.selfTraining.version0.model.Student;
import java.time.temporal.ChronoUnit;
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
    }

    public void returnBook(Librarian receivedBy, LocalDate returnDate) {
        this.isReturned = true;
        this.receivedBy = receivedBy;
        this.returnDate = returnDate;
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

    public boolean isOverdue() {
        if (isReturned) {
            return ChronoUnit.DAYS.between(dueDate, returnDate) > 0;
        }
        return ChronoUnit.DAYS.between(dueDate, LocalDate.now()) > 0;
    }

    @Override
    public String toString() {
        String returnInfo = isReturned ?
                ", Returned to: " + receivedBy.getFirstName() + " " + receivedBy.getLastName() +
                        ", Return Date: " + returnDate :
                "";
        return "Borrow: " + book.getTitle() + " by " + student.getFirstName() + " " + student.getLastName() +
                ", Issued by: " + issuedBy.getFirstName() + " " + issuedBy.getLastName() +
                ", Borrow Date: " + borrowDate + ", Due Date: " + dueDate + returnInfo;
    }
}
