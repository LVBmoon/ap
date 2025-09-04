package ap.exercises.finalTerm_project.version1.core;

import ap.exercises.finalTerm_project.version1.core.Borrow;
import ap.exercises.finalTerm_project.version1.core.BorrowRequest;
import ap.exercises.finalTerm_project.version1.model.Book;
import ap.exercises.finalTerm_project.version1.model.Librarian;
import ap.exercises.finalTerm_project.version1.model.Student;
import ap.exercises.finalTerm_project.version1.model.LibraryManager;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Library {
    private String name;
    private ArrayList<ap.exercises.finalTerm_project.version1.model.Book> books;
    private ArrayList<ap.exercises.finalTerm_project.version1.model.Student> students;
    private ArrayList<ap.exercises.finalTerm_project.version1.model.Librarian> librarians;
    private ArrayList<ap.exercises.finalTerm_project.version1.core.Borrow> borrows;
    private ArrayList<ap.exercises.finalTerm_project.version1.core.BorrowRequest> borrowRequests;
    private LibraryManager manager;

    public Library(String name, LibraryManager manager) {
        setName(name);
        this.manager = manager;
        this.books = new ArrayList<>();
        this.students = new ArrayList<>();
        this.librarians = new ArrayList<>();
        this.borrows = new ArrayList<>();
        this.borrowRequests = new ArrayList<>();
        librarians.add(new ap.exercises.finalTerm_project.version1.model.Librarian("LVB", "moon", "L001", "LVB!", "codingIsFun88", "nothing yet"));
        librarians.add(new ap.exercises.finalTerm_project.version1.model.Librarian("Dervish", "Grady", "L002", "TheGreatMoreed", "deathIsNotTheEnding", "unknown"));
        librarians.add(new ap.exercises.finalTerm_project.version1.model.Librarian("Lelouch", "Lamperouge", "L003", "TheKing", "CodeGeass", "High School Student"));
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

    public List<ap.exercises.finalTerm_project.version1.model.Book> getBooks() {
        return new ArrayList<>(books);
    }

    public List<ap.exercises.finalTerm_project.version1.model.Student> getStudents() {
        return new ArrayList<>(students);
    }

    public List<ap.exercises.finalTerm_project.version1.model.Librarian> getLibrarians() {
        return new ArrayList<>(librarians);
    }

    public List<ap.exercises.finalTerm_project.version1.core.Borrow> getBorrows() {
        return new ArrayList<>(borrows);
    }

    public List<ap.exercises.finalTerm_project.version1.core.BorrowRequest> getBorrowRequests() {
        return new ArrayList<>(borrowRequests);
    }

    public LibraryManager getManager() {
        return manager;
    }

    public boolean addBook(ap.exercises.finalTerm_project.version1.model.Book book) {
        if (findBookByBookId(book.getBookId()) != null) {
            return false;
        }
        return books.add(book);
    }

    public boolean addStudent(ap.exercises.finalTerm_project.version1.model.Student student) {
        return students.add(student);
    }

    public boolean addLibrarian(ap.exercises.finalTerm_project.version1.model.Librarian librarian) {
        return librarians.add(librarian);
    }

    public boolean addBorrow(ap.exercises.finalTerm_project.version1.core.Borrow borrow) {
        return borrows.add(borrow);
    }

    public boolean addBorrowRequest(ap.exercises.finalTerm_project.version1.core.BorrowRequest request) {
        return borrowRequests.add(request);
    }

    public List<ap.exercises.finalTerm_project.version1.model.Book> searchBooks(String title, Integer year, String author) {
        ArrayList<ap.exercises.finalTerm_project.version1.model.Book> results = new ArrayList<>();
        for (ap.exercises.finalTerm_project.version1.model.Book book : books) {
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

    public ap.exercises.finalTerm_project.version1.model.Book findBookByBookId(String bookId) {
        for (ap.exercises.finalTerm_project.version1.model.Book book : books) {
            if (book.getBookId().equalsIgnoreCase(bookId)) {
                return book;
            }
        }
        return null;
    }

    public ap.exercises.finalTerm_project.version1.model.Book findBookByTitle(String title) {
        for (ap.exercises.finalTerm_project.version1.model.Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public ap.exercises.finalTerm_project.version1.model.Student findStudentByStudentId(String studentId) {
        for (ap.exercises.finalTerm_project.version1.model.Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public ap.exercises.finalTerm_project.version1.model.Librarian findLibrarianByEmployeeId(String employeeId) {
        for (ap.exercises.finalTerm_project.version1.model.Librarian librarian : librarians) {
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

    public List<ap.exercises.finalTerm_project.version1.core.Borrow> getOverdueBorrows() {
        ArrayList<ap.exercises.finalTerm_project.version1.core.Borrow> overdue = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (ap.exercises.finalTerm_project.version1.core.Borrow borrow : borrows) {
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
        for (ap.exercises.finalTerm_project.version1.core.Borrow borrow : borrows) {
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
        for (ap.exercises.finalTerm_project.version1.core.BorrowRequest request : borrowRequests) {
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
        for (ap.exercises.finalTerm_project.version1.core.Borrow borrow : borrows) {
            if (borrow.isReturned()) {
                totalDays += ChronoUnit.DAYS.between(borrow.getBorrowDate(), borrow.getReturnDate());
                count++;
            }
        }
        return count == 0 ? 0.0 : (double) totalDays / count;
    }

    public List<ap.exercises.finalTerm_project.version1.model.Book> getMostBorrowedBooks(int limit) {
        ArrayList<ap.exercises.finalTerm_project.version1.model.Book> sortedBooks = new ArrayList<>(books);
        sortedBooks.sort(Comparator.comparingInt(Book::getBorrowCount).reversed());
        return sortedBooks.subList(0, Math.min(limit, sortedBooks.size()));
    }

    public List<ap.exercises.finalTerm_project.version1.model.Student> getTopDelayedStudents(int limit) {
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