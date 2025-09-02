package ap.exercises.finalTerm_project.version1.menu;

import ap.exercises.finalTerm_project.version1.core.Library;
import ap.exercises.finalTerm_project.version1.data.DataStorage;
import ap.exercises.finalTerm_project.version1.data.InputProcessor;
import ap.exercises.finalTerm_project.version1.model.Student;

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
            System.out.println("   2. Exit");
            int choice = inputProcessor.getIntInput("Please enter choice: ");
            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
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
}
