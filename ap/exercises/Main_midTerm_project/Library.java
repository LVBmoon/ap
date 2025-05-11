package ap.exercises.Main_midTerm_project;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private String libraryName;;
    private List<Student> students;
    private List<Book> books;

    public Library(String libraryName) {
        this.libraryName = libraryName;
        this.students = new ArrayList<>();
        this.books = new ArrayList<>();
    }

    public void setLibraryName(String libraryName) {
        if (!libraryName.isEmpty()) {
            this.libraryName = libraryName;
        } else {
            throw new IllegalArgumentException("Library name cannot be empty!");
        }
    }

    public void addBook(Book book) {
        if (book != null) {
            books.add(book);
        } else {
            throw new IllegalArgumentException("Book cannot be null!");
        }
    }

    public void addStudent(Student student) {
        if (student != null) {
            students.add(student);
        } else {
            throw new IllegalArgumentException("Student cannot be null!");
        }
    }




}
