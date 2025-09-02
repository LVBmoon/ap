package ap.exercises.finalTerm_project.version1.menu;

import ap.exercises.finalTerm_project.version1.core.Library;
import ap.exercises.finalTerm_project.version1.data.DataStorage;
import ap.exercises.finalTerm_project.version1.data.InputProcessor;
import ap.exercises.finalTerm_project.version1.model.Book;
import ap.exercises.finalTerm_project.version1.model.Student;
import java.util.ArrayList;

public class StudentMenu extends Menu {
    private Student student;

    public StudentMenu(Library library, InputProcessor inputProcessor) {
        super(library, inputProcessor);
        this.student = null;
    }

    @Override
    public void showMenu() {
        while (true) {
            System.out.println("\n=== Student Menu ===");
            System.out.println("   1. Register");
            System.out.println("   2. Login");
            System.out.println("   3. Search Books");
            System.out.println("   4. Exit");
            int choice = inputProcessor.getIntInput("Please enter choice: ");
            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    searchBooks();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
            new DataStorage().saveLibrary(library);
        }
    }

    private void register() {
        String firstName = inputProcessor.getStringInput("Enter first name: ");
        String lastName = inputProcessor.getStringInput("Enter last name: ");
        String studentId = inputProcessor.getStringInput("Enter student ID: ");
        String fieldOfStudy = inputProcessor.getStringInput("Enter field of study: ");
        String username = inputProcessor.getStringInput("Enter username: ");
        String password = inputProcessor.getStringInput("Enter password: ");

        Student existing = library.findStudentByStudentId(studentId);
        if (existing != null) {
            System.out.println("Student ID already exists!");
            return;
        }
        for (Student s : library.getStudents()) {
            if (s.getUsername().equals(username)) {
                System.out.println("Username already exists!");
                return;
            }
        }

        try {
            Student newStudent = new Student(firstName, lastName, studentId, fieldOfStudy, username, password, library);
            library.addStudent(newStudent);
            System.out.println("Registration successful.");
        } catch (IllegalArgumentException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    private void login() {
        if (library.getStudents().isEmpty()) {
            System.out.println("No students loaded. Please register or check data files (students.txt).");
            return;
        }
        String username = inputProcessor.getStringInput("Enter username: ");
        String password = inputProcessor.getStringInput("Enter password: ");
        for (Student s : library.getStudents()) {
            if (s.getUsername().equals(username) && s.checkPassword(password)) {
                student = s;
                System.out.println("Login successful.");
                return;
            }
        }
        System.out.println("Invalid username or password.");
    }

    private void searchBooks() {
        String title = inputProcessor.getStringInput("Enter title (or leave empty): ");
        String author = inputProcessor.getStringInput("Enter author (or leave empty): ");
        String year = inputProcessor.getStringInput("Enter publication year (or leave empty): ");
        ArrayList<Book> results = library.searchBooks(title, author, year);
        if (results.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("Search results:");
            for (Book book : results) {
                System.out.println(book);
            }
        }
    }
}
