package ap.exercises.midTerm_project.version2_part2.core;

import ap.exercises.midTerm_project.version2_part2.model.Book;
import ap.exercises.midTerm_project.version2_part2.model.Librarian;
import ap.exercises.midTerm_project.version2_part2.model.LibraryManager;
import ap.exercises.midTerm_project.version2_part2.model.Student;

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

    public Book findBookByTitle(String title) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equalsIgnoreCase(title)) {
                return books.get(i);
            }
        }
        return null;
    }

    public Student findStudentByStudentId(String studentId) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(studentId)) {
                return students.get(i);
            }
        }
        return null;
    }

    public Librarian findLibrarianByEmployeeId(String employeeId) {
        for (int i = 0; i < librarians.size(); i++) {
            if (librarians.get(i).getEmployeeId().equals(employeeId)) {
                return librarians.get(i);
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
        for (int i = 0; i < borrows.size(); i++) {
            if (borrows.get(i).isOverdue()) {
                overdue.add(borrows.get(i));
            }
        }
        return overdue;
    }

    public ArrayList<Book> getMostBorrowedBooks(int count) {
        ArrayList<Book> sortedBooks = new ArrayList<>(books);
        for (int i = 0; i < sortedBooks.size() - 1; i++) {
            for (int j = 0; j < sortedBooks.size() - i - 1; j++) {
                if (sortedBooks.get(j).getBorrowCount() < sortedBooks.get(j + 1).getBorrowCount()) {
                    Book temp = sortedBooks.get(j);
                    sortedBooks.set(j, sortedBooks.get(j + 1));
                    sortedBooks.set(j + 1, temp);
                }
            }
        }
        ArrayList<Book> result = new ArrayList<>();
        for (int i = 0; i < sortedBooks.size() && i < count; i++) {
            result.add(sortedBooks.get(i));
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