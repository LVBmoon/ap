package ap.exercises.midTerm_project.version2;

import java.io.Serializable;
import java.util.Date;

public class Loan implements Serializable {
    private Book book;
    private Student student;
    private Librarian issuedBy;
    private Librarian receivedBy;
    private Date loanDate;
    private Date dueDate;
    private Date returnDate;
    private boolean isReturned;

    public Loan(Book book, Student student, Librarian issuedBy, Date loanDate) {
        if (book == null || student == null || issuedBy == null || loanDate == null) {
            throw new IllegalArgumentException("Loan parameters cannot be null!");
        }
        this.book = book;
        this.student = student;
        this.issuedBy = issuedBy;
        this.loanDate = loanDate;

        // Calculate due date (2 weeks from loan date ^-^)
        long twoWeeksInMillis = 14 * 24 * 60 * 60 * 1000L;
        this.dueDate = new Date(loanDate.getTime() + twoWeeksInMillis);

        this.isReturned = false;
        issuedBy.incrementProcessedLoans();
    }

    // Getters
    public Book getBook() { return book; }
    public Student getStudent() { return student; }
    public Librarian getIssuedBy() { return issuedBy; }
    public Librarian getReceivedBy() { return receivedBy; }
    public Date getLoanDate() { return loanDate; }
    public Date getDueDate() { return dueDate; }
    public Date getReturnDate() { return returnDate; }
    public boolean isReturned() { return isReturned; }

    public void returnBook(Librarian receivedBy, Date returnDate) {
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
            return new Date().after(dueDate);
        }
        return returnDate.after(dueDate);
    }

    @Override
    public String toString() {
        String result = "Loan: " + book.getTitle() + " to " + student.getFirstName() + " " +
                student.getLastName() + " issued by " + issuedBy.getFirstName() +
                " " + issuedBy.getLastName() + " on " + loanDate + " (Due: " + dueDate + ")";

        if (isReturned) {
            result += " - Returned on " + returnDate + " by " + receivedBy.getFirstName() +
                    " " + receivedBy.getLastName();
            if (isOverdue()) {
                result += " (OVERDUE)";
            }
        } else if (isOverdue()) {
            result += " - CURRENTLY OVERDUE!";
        }

        return result;
    }
}
