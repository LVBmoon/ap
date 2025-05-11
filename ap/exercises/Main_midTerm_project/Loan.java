package ap.exercises.Main_midTerm_project;

import java.util.List;
import java.util.Random;
import java.time.LocalDate;

public class Loan {
    private Book book;
    private Student student;
    private LocalDate dueDate;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private Librarian issuingLibrarian;
    private Librarian receivingLibrarian;
    Random random = new Random();

    private static List<Loan> activeLoans = new java.util.ArrayList<>();
    private static List<Loan> overdueLoans = new java.util.ArrayList<>();


    public Loan(Book book, Student student, List<Librarian> librarians, LocalDate loanDate, LocalDate shouldReturnDate) {
        if (book.isAvailable()) {
            this.book = book;
            this.student = student;
            this.loanDate = loanDate;
            this.dueDate = shouldReturnDate;
            this.returnDate = null;
            this.issuingLibrarian = librarians.get(random.nextInt(librarians.size()));

            book.setAvailable(false);
            activeLoans.add(this);
            HistoryHandler.saveLoanHistory(this);
        }
        else{
            throw new IllegalArgumentException("Book is not available for loan now!");
        }
    }

    public Book getBook() {
        return book;
    }

    public Student getStudent() {
        return student;
    }

    public Librarian getIssuingLibrarian() {
        return issuingLibrarian;
    }

    public Librarian getReceivingLibrarian() {
        return receivingLibrarian;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public boolean isOverdue() {
        return returnDate == null && LocalDate.now().isAfter(dueDate);
    }

    public List<Loan> getActiveLoans() {
        return activeLoans;
    }

    public List<Loan> getOverdueLoans() {
        return overdueLoans;
    }

    public void completeLoan(Librarian receivingLibrarian, LocalDate returnDate) {
        if (this.dueDate == null) {
            this.dueDate = returnDate;
            this.receivingLibrarian = receivingLibrarian;
            book.setAvailable(true);

            HistoryHandler.updateLoanHistory(this);
        } else {
            throw new IllegalStateException("This loan is already completed.");
        }
    }
    public void returnBook(Librarian receivingLibrarian) {
        if (this.returnDate == null) {
            this.returnDate = LocalDate.now();
            this.receivingLibrarian = receivingLibrarian;
            book.setAvailable(true);
            activeLoans.remove(this);

            if (isOverdue()) {
                overdueLoans.add(this);//it will add to overdue list
            }
            HistoryHandler.updateLoanHistory(this);
        }
        else {
            throw new IllegalStateException("Book is already returned.");
        }
    }

    @Override
    public String toString() {
        return "Book: " + book.getTitle() + " | Borrower: " + student.getFirstName() + " " + student.getLastName() +
                " | Issued by: " + issuingLibrarian.getFirstName() + " " + issuingLibrarian.getLastName() +
                " | Loan Date: " + loanDate + " | Due Date: " + dueDate +
                " | Return Date: " + (returnDate != null ? returnDate : "Not Returned");
    }
}
