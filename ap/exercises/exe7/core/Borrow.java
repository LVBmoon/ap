package ap.exercises.exe7.core;

import ap.exercises.exe7.model.Book;
import ap.exercises.exe7.model.Librarian;
import ap.exercises.exe7.model.Student;

import java.time.LocalDate;

public class Borrow {
    private Book book;
    private Student student;
    private Librarian issuedBy;
    private Librarian receivedBy;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private boolean isReturned;

    public Borrow(Book book, Student student, Librarian issuedBy, LocalDate borrowDate) {
        if (book == null || student == null || issuedBy == null || borrowDate == null) {
            throw new IllegalArgumentException("Borrow parameters cannot be null!");
        }
        this.book = book;
        this.student = student;
        this.issuedBy = issuedBy;
        this.borrowDate = borrowDate;
        this.dueDate = borrowDate.plusWeeks(2);
        this.isReturned = false;
        issuedBy.incrementProcessedLoans();
    }

    public void returnBook(Librarian receivedBy, LocalDate returnDate) {
        if (receivedBy == null || returnDate == null) {
            throw new IllegalArgumentException("Return parameters cannot be null!");
        }
        this.receivedBy = receivedBy;
        this.returnDate = returnDate;
        this.isReturned = true;
        receivedBy.incrementProcessedLoans();
    }

    public boolean isOverdue() {
        if (!isReturned) {
            return LocalDate.now().isAfter(dueDate);
        }
        return returnDate.isAfter(dueDate);
    }

    // Getters
    public Book getBook() {
        return book;
    }

    public Student getStudent() {
        return student;
    }

    public Librarian getIssuedBy() {
        return issuedBy;
    }

    public Librarian getReceivedBy() {
        return receivedBy;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    @Override
    public String toString() {
        String result = "Borrow: " + book.getTitle() + " to " + student.getFirstName() + " " +
                student.getLastName() + " (" + student.getStudentId() + "), Field: " +
                student.getFieldOfStudy() + ", Debt: " + student.calculateDebt() +
                " IRR, Issued by: " + issuedBy.getFirstName() + " " + issuedBy.getLastName() +
                ", Borrow Date: " + borrowDate + ", Due: " + dueDate;
        if (isReturned) {
            result += ", Returned on: " + returnDate + ", Received by: " +
                    receivedBy.getFirstName() + " " + receivedBy.getLastName();
            if (isOverdue()) {
                result += " (OVERDUE)";
            }
        } else if (isOverdue()) {
            result += " - CURRENTLY OVERDUE!";
        }
        return result;
    }
}