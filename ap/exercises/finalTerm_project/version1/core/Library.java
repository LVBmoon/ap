package ap.exercises.finalTerm_project.version1.core;
//completed
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import ap.exercises.finalTerm_project.version1.core.Borrow;
import ap.exercises.finalTerm_project.version1.core.BorrowRequest;
import ap.exercises.finalTerm_project.version1.model.Book;
import ap.exercises.finalTerm_project.version1.model.Librarian;
import ap.exercises.finalTerm_project.version1.model.Student;
import ap.exercises.finalTerm_project.version1.model.LibraryManager;


public class Library {
    private String name;
    private ArrayList<Book> books;
    private ArrayList<Student> students;
    private ArrayList<Librarian> librarians;
    private ArrayList<Borrow> borrows;
    private ArrayList<BorrowRequest> borrowRequests;
    private LibraryManager manager;

    public Library(String name, LibraryManager manager) {
        setName(name);
        this.manager = manager;
        this.books = new ArrayList<>();
        this.students = new ArrayList<>();
        this.librarians = new ArrayList<>();
        this.borrows = new ArrayList<>();
        this.borrowRequests = new ArrayList<>();
        librarians.add(new Librarian("LVB", "moon", "L001", "LVB!", "codingIsFun88", "nothing yet"));
        librarians.add(new Librarian("Dervish", "Grady", "L002", "TheGreatMoreed", "deathIsNotTheEnding", "unknown"));
        librarians.add(new Librarian("Lelouch", "Lamperouge", "L003", "TheKing", "CodeGeass", "High School Student"));
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Library name cannot be empty!");
        }
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public List<Librarian> getLibrarians() {
        return new ArrayList<>(librarians);
    }

    public List<Borrow> getBorrows() {
        return new ArrayList<>(borrows);
    }

    public List<BorrowRequest> getBorrowRequests() {
        return new ArrayList<>(borrowRequests);
    }

    public LibraryManager getManager() {
        return manager;
    }

    public boolean addBook(Book book) {
        if (findBookByBookId(book.getBookId()) != null) {
            return false;
        }
        return books.add(book);
    }

    public boolean addStudent(Student student) {
        return students.add(student);
    }

    public boolean addLibrarian(Librarian librarian) {
        return librarians.add(librarian);
    }

    public boolean addBorrow(Borrow borrow) {
        return borrows.add(borrow);
    }

    public boolean addBorrowRequest(BorrowRequest request) {
        return borrowRequests.add(request);
    }

    public List<Book> searchBooks(String title, Integer year, String author) {
        ArrayList<Book> results = new ArrayList<>();
        for (Book book : books) {
            boolean match = true;
            if (title != null && !title.isEmpty() && !book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                match = false;
            }
            if (year != null && book.getPublicationYear() != year) {
                match = false;
            }
            if (author != null && !author.isEmpty() && !book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                match = false;
            }
            if (match) {
                results.add(book);
            }
        }
        return results;
    }

    public Book findBookByBookId(String bookId) {
        for (Book book : books) {
            if (book.getBookId().equalsIgnoreCase(bookId)) {
                return book;
            }
        }
        return null;
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public Student findStudentByStudentId(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public Librarian findLibrarianByEmployeeId(String employeeId) {
        for (Librarian librarian : librarians) {
            if (librarian.getEmployeeId().equals(employeeId)) {
                return librarian;
            }
        }
        return null;
    }

    public Librarian getRandomLibrarian() {
        Random random = new Random();
        int index = random.nextInt(librarians.size());
        return librarians.get(index);
    }

    public List<Borrow> getOverdueBorrows() {
        ArrayList<Borrow> overdue = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (Borrow borrow : borrows) {
            if (!borrow.isReturned() && borrow.getDueDate().isBefore(today)) {
                overdue.add(borrow);
            }
        }
        return overdue;
    }

    public int getTotalStudents() {
        return students.size();
    }

    public int getTotalBooks() {
        return books.size();
    }

    public int getTotalBorrows() {
        return borrows.size();
    }

    public int getCurrentBorrows() {
        int count = 0;
        for (Borrow borrow : borrows) {
            if (!borrow.isReturned()) {
                count++;
            }
        }
        return count;
    }

    public int getTotalBorrowRequests() {
        return borrowRequests.size();
    }

    public int getTotalApprovedBorrows() {
        int count = 0;
        for (BorrowRequest request : borrowRequests) {
            if (request.getStatus() == BorrowRequest.RequestStatus.APPROVED && !request.isReturnRequest()) {
                count++;
            }
        }
        return count;
    }

    public double getAverageBorrowDays() {
        if (borrows.isEmpty()) {
            return 0.0;
        }
        long totalDays = 0;
        int count = 0;
        for (Borrow borrow : borrows) {
            if (borrow.isReturned()) {
                totalDays += ChronoUnit.DAYS.between(borrow.getBorrowDate(), borrow.getReturnDate());
                count++;
            }
        }
        return count == 0 ? 0.0 : (double) totalDays / count;
    }

    public List<Book> getMostBorrowedBooks(int limit) {
        ArrayList<Book> sortedBooks = new ArrayList<>(books);
        sortedBooks.sort(Comparator.comparingInt(Book::getBorrowCount).reversed());
        return sortedBooks.subList(0, Math.min(limit, sortedBooks.size()));
    }

    public List<Student> getTopDelayedStudents(int limit) {
        ArrayList<Student> sortedStudents = new ArrayList<>(students);
        sortedStudents.sort((s1, s2) -> {
            long delay1 = 0, delay2 = 0;
            for (Borrow borrow : borrows) {
                if (borrow.getStudent().getStudentId().equals(s1.getStudentId()) && borrow.isOverdue()) {
                    LocalDate endDate = borrow.isReturned() ? borrow.getReturnDate() : LocalDate.now();
                    delay1 += ChronoUnit.DAYS.between(borrow.getDueDate(), endDate);
                }
                if (borrow.getStudent().getStudentId().equals(s2.getStudentId()) && borrow.isOverdue()) {
                    LocalDate endDate = borrow.isReturned() ? borrow.getReturnDate() : LocalDate.now();
                    delay2 += ChronoUnit.DAYS.between(borrow.getDueDate(), endDate);
                }
            }
            return Long.compare(delay2, delay1);
        });
        return sortedStudents.subList(0, Math.min(limit, sortedStudents.size()));
    }
}