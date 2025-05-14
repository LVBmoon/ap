package ap.exercises.midTerm_project.version1;

import java.io.Serializable;
import java.time.LocalDate;

public class Loan implements Serializable {
    private Book book;
    private Student student;
    private Librarian librarian;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private Librarian receivingLibrarian;

    public Loan(Book book, Student student, Librarian librarian, LocalDate loanDate, LocalDate dueDate) {
        this.book = book;
        this.student = student;
        this.librarian = librarian;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
    }

    // Getters and 2 Setters
    public Book getBook() { return book; }
    public Student getStudent() { return student; }
    public Librarian getLibrarian() { return librarian; }
    public LocalDate getLoanDate() { return loanDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public Librarian getReceivingLibrarian() { return receivingLibrarian; }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setReceivingLibrarian(Librarian receivingLibrarian) {
        this.receivingLibrarian = receivingLibrarian;
    }

    public boolean isOverdue() {
        return LocalDate.now().isAfter(dueDate) && returnDate == null;
    }
}
