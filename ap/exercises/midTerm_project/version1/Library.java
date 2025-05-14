package ap.exercises.midTerm_project.version1;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Library implements Serializable {
    private String name;
    private List<Book> books;
    private List<Student> students;
    private List<Librarian> librarians;
    private List<Loan> loans;
    private LibraryManager manager;

    public Library(String name, LibraryManager manager) {
        this.name = name;
        this.manager = manager;
        this.books = new ArrayList<>();
        this.students = new ArrayList<>();
        this.librarians = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    // Getters
    public String getName() { return name; }
    public List<Book> getBooks() { return books; }
    public List<Student> getStudents() { return students; }
    public List<Librarian> getLibrarians() { return librarians; }
    public List<Loan> getLoans() { return loans; }
    public LibraryManager getManager() { return manager; }

    // Methods to manage books
    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> searchBooks(String keyword) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                        book.getAuthor().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Methods for managing students
    public void registerStudent(Student student) {
        students.add(student);
    }

    // Methods for managing librarians
    public void addLibrarian(Librarian librarian) {
        librarians.add(librarian);
    }

    // Methods for managing loans
    public boolean borrowBook(Student student, Book book, Librarian librarian) {
        if (book.isAvailable() && students.contains(student) && librarians.contains(librarian)) {
            LocalDate loanDate = LocalDate.now();
            LocalDate dueDate = loanDate.plus(14, ChronoUnit.DAYS); // I set 2 weeks loan period for example

            Loan loan = new Loan(book, student, librarian, loanDate, dueDate);
            loans.add(loan);
            student.borrowBook(book);
            librarian.incrementProcessedBooks();
            return true;
        }
        return false;
    }

    public boolean returnBook(Student student, Book book, Librarian librarian) {
        Optional<Loan> optionalLoan = loans.stream()
                .filter(loan -> loan.getBook().equals(book) &&
                        loan.getStudent().equals(student) &&
                        loan.getReturnDate() == null)
                .findFirst();

        if (optionalLoan.isPresent()) {
            Loan loan = optionalLoan.get();
            loan.setReturnDate(LocalDate.now());
            loan.setReceivingLibrarian(librarian);
            student.returnBook(book);
            librarian.incrementProcessedBooks();
            return true;
        }
        return false;
    }

    // Report methods for manager
    public List<Loan> getOverdueLoans() {
        return loans.stream()
                .filter(Loan::isOverdue)
                .collect(Collectors.toList());
    }

    public Map<Librarian, Integer> getLibrarianStatistics() {
        Map<Librarian, Integer> stats = new HashMap<>();
        for (Librarian librarian : librarians) {
            stats.put(librarian, librarian.getBooksProcessed());
        }
        return stats;
    }

    public List<Book> getMostBorrowedBooks(int limit) {
        Map<Book, Long> bookCounts = loans.stream()
                .collect(Collectors.groupingBy(Loan::getBook, Collectors.counting()));

        return bookCounts.entrySet().stream()
                .sorted(Map.Entry.<Book, Long>comparingByValue().reversed())
                .limit(limit)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    // Save and load methods
    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
        }
    }

    public static Library loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Library) ois.readObject();
        }
    }
}
