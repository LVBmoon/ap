package src.selfTraining.version0.core;

import src.selfTraining.version0.model.Book;
import src.selfTraining.version0.model.Librarian;
import src.selfTraining.version0.model.Student;
import src.selfTraining.version0.model.LibraryManager;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

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

    public ArrayList<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public ArrayList<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public ArrayList<Librarian> getLibrarians() {
        return new ArrayList<>(librarians);
    }

    public ArrayList<Borrow> getBorrows() {
        return new ArrayList<>(borrows);
    }

    public ArrayList<BorrowRequest> getBorrowRequests() {
        return new ArrayList<>(borrowRequests);
    }

    public LibraryManager getManager() {
        return manager;
    }

    public boolean addBook(Book book) {
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

    // Advanced search for requirement 1-3
    public ArrayList<Book> searchBooks(String title, Integer year, String author) {
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

    public ArrayList<Borrow> getOverdueBorrows() {
        ArrayList<Borrow> overdue = new ArrayList<>();
        for (Borrow borrow : borrows) {
            if (borrow.isOverdue()) {
                overdue.add(borrow);
            }
        }
        return overdue;
    }

    public ArrayList<Book> getMostBorrowedBooks(int count) {
        ArrayList<Book> sortedBooks = new ArrayList<>(books);
        sortedBooks.sort((b1, b2) -> b2.getBorrowCount() - b1.getBorrowCount());
        ArrayList<Book> result = new ArrayList<>();
        for (int i = 0; i < sortedBooks.size() && i < count; i++) {
            result.add(sortedBooks.get(i));
        }
        return result;
    }

    // Guest stats for requirement 2-3
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

    // Manager stats for requirement 4-3
    public int getTotalBorrowRequests() {
        return borrowRequests.size();
    }

    public int getTotalApprovedBorrows() {
        int count = 0;
        for (BorrowRequest request : borrowRequests) {
            if (request.getStatus().equals("APPROVED") && !request.isReturnRequest()) {
                count++;
            }
        }
        return count;
    }

    public double getAverageBorrowDays() {
        long totalDays = 0;
        int count = 0;
        for (Borrow borrow : borrows) {
            if (borrow.isReturned()) {
                long days = ChronoUnit.DAYS.between(borrow.getBorrowDate(), borrow.getReturnDate());
                totalDays += days;
                count++;
            }
        }
        return count > 0 ? (double) totalDays / count : 0;
    }

    // Top delayed students for requirement 4-4
    public ArrayList<Student> getTopDelayedStudents(int count) {
        ArrayList<Student> studentsCopy = new ArrayList<>(students);
        studentsCopy.sort((s1, s2) -> {
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
        ArrayList<Student> result = new ArrayList<>();
        for (int i = 0; i < studentsCopy.size() && i < count; i++) {
            result.add(studentsCopy.get(i));
        }
        return result;
    }

    @Override
    public String toString() {
        return name + " Library - Books: " + books.size() +
                "| Students: " + students.size() +
                "| Librarians: " + librarians.size() +
                "| Borrows: " + borrows.size() +
                "| Requests: " + borrowRequests.size();
    }
}