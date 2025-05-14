package ap.exercises.midTerm_project.version2_part1;

import java.io.*;
import java.util.ArrayList;
//import java.util.Date;  // what should I do?

public class Library implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private ArrayList<Book> books;
    private ArrayList<Student> students;
    private ArrayList<Librarian> librarians;
    private ArrayList<Loan> loans;
    private LibraryManager manager;

    public Library(String name, LibraryManager manager) {
        setName(name);
        this.manager = manager;
        this.books = new ArrayList<>();
        this.students = new ArrayList<>();
        this.librarians = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    // Setter :
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Library name cannot be empty!");
        }
        this.name = name.trim();
    }

    // Getters
    public String getName() { return name; }
    public ArrayList<Book> getBooks() { return new ArrayList<>(books); }
    public ArrayList<Student> getStudents() { return new ArrayList<>(students); }
    public ArrayList<Librarian> getLibrarians() { return new ArrayList<>(librarians); }
    public ArrayList<Loan> getLoans() { return new ArrayList<>(loans); }
    public LibraryManager getManager() { return manager; }

    // Methods for adding :
    public boolean addBook(Book book) {
        return books.add(book);
    }

    public boolean addStudent(Student student) {
        return students.add(student);
    }

    public boolean addLibrarian(Librarian librarian) {
        return librarians.add(librarian);
    }

    public boolean addLoan(Loan loan) {
        return loans.add(loan);
    }

    // Search methods :
    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public Student findStudentByUsername(String username) {
        for (Student student : students) {
            if (student.getUsername().equals(username)) {
                return student;
            }
        }
        return null;
    }

    public Librarian findLibrarianByUsername(String username) {
        for (Librarian librarian : librarians) {
            if (librarian.getUsername().equals(username)) {
                return librarian;
            }
        }
        return null;
    }

    // Other methods:
    public ArrayList<Loan> getOverdueLoans() {
        ArrayList<Loan> overdueLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.isOverdue()) {
                overdueLoans.add(loan);
            }
        }
        return overdueLoans;
    }

    public ArrayList<Book> getMostBorrowedBooks(int year, int count) {
        // Simplified implementation - I don't know how to make it much more like reality :(
        ArrayList<Book> result = new ArrayList<>();
        int endIndex = Math.min(count, books.size());
        for (int i = 0; i < endIndex; i++) {
            result.add(books.get(i));
        }
        return result;
    }

    // Save and load methods :
    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
        } catch (IOException e) {
            System.err.println("Error saving library data: " + e.getMessage());
        }
    }

    public static Library loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Library) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading library data: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String toString() {
        return name + " Library - Books: " + books.size() +
                "| Students: " + students.size() +
                "| Librarians: " + librarians.size() +
                "| Loans: " + loans.size();
    }
}
