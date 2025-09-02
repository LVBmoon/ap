package ap.exercises.finalTerm_project.version1.core;

import ap.exercises.finalTerm_project.version1.model.Book;
import ap.exercises.finalTerm_project.version1.model.Student;
import java.util.ArrayList;

public class Library {
    private String name;
    private ArrayList<Student> students;
    private ArrayList<Book> books;

    public Library(String name) {
        setName(name);
        this.students = new ArrayList<>();
        this.books = new ArrayList<>();
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

    public ArrayList<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public ArrayList<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public boolean addStudent(Student student) {
        return students.add(student);
    }

    public boolean addBook(Book book) {
        return books.add(book);
    }

    public Student findStudentByStudentId(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public ArrayList<Book> searchBooks(String title, String author, String year) {
        ArrayList<Book> results = new ArrayList<>();
        int searchYear = year.isEmpty() ? -1 : Integer.parseInt(year);
        for (Book book : books) {
            boolean matches = true;
            if (!title.isEmpty() && !book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                matches = false;
            }
            if (!author.isEmpty() && !book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                matches = false;
            }
            if (searchYear != -1 && book.getPublicationYear() != searchYear) {
                matches = false;
            }
            if (matches) {
                results.add(book);
            }
        }
        return results;
    }

    @Override
    public String toString() {
        return name + " Library - Students: " + students.size() + ", Books: " + books.size();
    }
}