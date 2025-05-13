package ap.exercises.Main_midTerm_project;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private String libraryName;;
    private List<Student> students;
    private List<Book> books;
    private List<Librarian> librarians;
    private LibraryManager manager;


    public Library(String libraryName) {
        setLibraryName(libraryName);
        this.students = new ArrayList<>();
        this.books = new ArrayList<>();
        this.librarians = new ArrayList<>();
    }

    public String getLibraryName() {
        return libraryName;
    }
    public void setLibraryName(String libraryName) {
        if (!libraryName.isEmpty()) {
            this.libraryName = libraryName;
        }
        else {
            throw new IllegalArgumentException("Library name cannot be empty!");
        }
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Librarian> getLibrarians() {
        return librarians;
    }

    public LibraryManager getManager() {
        return manager;
    }

    public void addBook(Book book) {
        if (book != null) {
            books.add(book);
        }
        else {
            throw new IllegalArgumentException("Book cannot be null!");
        }
    }

    public void addStudent(Student student) {
        if (student != null) {
            students.add(student);
        }
        else {
            throw new IllegalArgumentException("Student cannot be null!");
        }
    }
    private boolean isStudentIDExist(int studentID) {
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                return true;
            }
        }
        return false;
    }

    public void addLibrarian(Librarian librarian) {
        if (librarian != null) {
            librarians.add(librarian);
        }
        else {
            throw new IllegalArgumentException("Librarian cannot be null!");
        }
    }
    private boolean isLibrarianIDExist(int librarianID) {
        for (Librarian librarian : librarians) {
            if (librarian.getLibrarianID() == librarianID) {
                return true;
            }
        }
        return false;
    }



}
