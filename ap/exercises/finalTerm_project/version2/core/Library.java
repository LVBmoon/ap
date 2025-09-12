package ap.exercises.finalTerm_project.version2.core;

import ap.exercises.finalTerm_project.version2.model.Book;
import ap.exercises.finalTerm_project.version2.model.Librarian;
import ap.exercises.finalTerm_project.version2.model.LibraryManager;
import ap.exercises.finalTerm_project.version2.model.Student;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private String name;
    private Map<String, Book> books;
    private Map<String, Student> students;
    private Map<String, Librarian> librarians;
    private List<Borrow> borrows;
    private List<BorrowRequest> borrowRequests;
    private LibraryManager manager;

    public Library(String name, LibraryManager manager) {
        setName(name);
        this.manager = manager;
        this.books = new HashMap<>();
        this.students = new HashMap<>();
        this.librarians = new HashMap<>();
        this.borrows = new ArrayList<>();
        this.borrowRequests = new ArrayList<>();
        // hardcoded librarians با key uppercase employeeId برای case-insensitive
        librarians.put("L001".toUpperCase(), new Librarian("LVB", "moon", "L001", "LVB!", "codingIsFun88", "nothing yet"));
        librarians.put("L002".toUpperCase(), new Librarian("Dervish", "Grady", "L002", "TheGreatMoreed", "deathIsNotTheEnding", "unknown"));
        librarians.put("L003".toUpperCase(), new Librarian("Lelouch", "Lamperouge", "L003", "TheKing", "CodeGeass", "High School Student"));
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
        return new ArrayList<>(books.values());
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students.values());
    }

    public List<Librarian> getLibrarians() {
        return new ArrayList<>(librarians.values());
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
        if (books.containsKey(book.getBookId().toUpperCase())) {
            return false;
        }
        books.put(book.getBookId().toUpperCase(), book);
        return true;
    }

    public boolean addStudent(Student student) {
        students.put(student.getStudentId().toUpperCase(), student);
        return true;
    }

    public boolean addLibrarian(Librarian librarian) {
        librarians.put(librarian.getEmployeeId().toUpperCase(), librarian);
        return true;
    }

    public boolean addBorrow(Borrow borrow) {
        return borrows.add(borrow);
    }

    public boolean addBorrowRequest(BorrowRequest request) {
        return borrowRequests.add(request);
    }

    public List<Book> searchBooks(String title, Integer year, String author) {
        return getBooks().stream()
                .filter(book -> {
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
                    return match;
                })
                .collect(Collectors.toList());
    }

    public Book findBookByBookId(String bookId) {
        return books.get(bookId.toUpperCase());
    }

    public Book findBookByTitle(String title) {
        return getBooks().stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst().orElse(null);
    }

    public Student findStudentByStudentId(String studentId) {
        return students.get(studentId.toUpperCase());
    }

    public Librarian findLibrarianByEmployeeId(String employeeId) {
        return librarians.get(employeeId.toUpperCase());
    }

    public Librarian getRandomLibrarian() {
        List<Librarian> libList = new ArrayList<>(librarians.values());
        Random random = new Random();
        int index = random.nextInt(libList.size());
        return libList.get(index);
    }

    public List<Borrow> getOverdueBorrows() {
        LocalDate today = LocalDate.now();
        return borrows.stream()
                .filter(borrow -> !borrow.isReturned() && borrow.getDueDate().isBefore(today))
                .collect(Collectors.toList());
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
        return (int) borrows.stream().filter(borrow -> !borrow.isReturned()).count();
    }

    public int getTotalBorrowRequests() {
        return borrowRequests.size();
    }

    public int getTotalApprovedBorrows() {
        return (int) borrowRequests.stream()
                .filter(request -> request.getStatus() == BorrowRequest.RequestStatus.APPROVED && !request.isReturnRequest())
                .count();
    }

    public double getAverageBorrowDays() {
        if (borrows.isEmpty()) {
            return 0.0;
        }
        List<Borrow> returnedBorrows = borrows.stream()
                .filter(Borrow::isReturned)
                .collect(Collectors.toList());
        if (returnedBorrows.isEmpty()) {
            return 0.0;
        }
        long totalDays = returnedBorrows.stream()
                .mapToLong(borrow -> ChronoUnit.DAYS.between(borrow.getBorrowDate(), borrow.getReturnDate()))
                .sum();
        return (double) totalDays / returnedBorrows.size();
    }

    public List<Book> getMostBorrowedBooks(int limit) {
        return getBooks().stream()
                .sorted(Comparator.comparingInt(Book::getBorrowCount).reversed())
                .limit(Math.min(limit, getBooks().size()))
                .collect(Collectors.toList());
    }

    public List<Student> getTopDelayedStudents(int limit) {
        Map<String, Long> delayMap = borrows.stream()
                .filter(Borrow::isOverdue)
                .collect(Collectors.groupingBy(
                        borrow -> borrow.getStudent().getStudentId(),
                        Collectors.summingLong(borrow -> {
                            LocalDate endDate = borrow.isReturned() ? borrow.getReturnDate() : LocalDate.now();
                            return ChronoUnit.DAYS.between(borrow.getDueDate(), endDate);
                        })
                ));
        return getStudents().stream()
                .sorted(Comparator.comparingLong(student -> -delayMap.getOrDefault(student.getStudentId(), 0L)))
                .limit(Math.min(limit, getStudents().size()))
                .collect(Collectors.toList());
    }
}