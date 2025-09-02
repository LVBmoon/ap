package ap.exercises.finalTerm_project.version1.data;

import ap.exercises.finalTerm_project.version1.core.Library;
import ap.exercises.finalTerm_project.version1.model.Student;
import java.io.*;

public class DataStorage {
    private static final String STUDENTS_FILE = "students.txt";

    private String escapeField(String field) {
        if (field == null) {
            return "";
        }
        return field.replace(",", "_").replace("|", "_");
    }

    private String unescapeField(String field) {
        if (field == null || field.isEmpty() || field.equals("_")) {
            return "";
        }
        return field.replace("_", " ");
    }

    public void saveLibrary(Library library) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(STUDENTS_FILE))) {
            for (Student student : library.getStudents()) {
                writer.println(escapeField(student.getFirstName()) + "," +
                        escapeField(student.getLastName()) + "," +
                        escapeField(student.getStudentId()) + "," +
                        escapeField(student.getFieldOfStudy()) + "," +
                        student.getJoinDate() + "," +
                        escapeField(student.getUsername()) + "," +
                        escapeField(student.getPassword()) + "," +
                        student.isActive());
            }
        } catch (IOException e) {
            System.err.println("Error saving students: " + e.getMessage());
        }
    }

    public Library loadLibrary() {
        Library library = new Library("University Central Library");
        int studentCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length < 8) {
                    System.err.println("Invalid student line format: " + line);
                    continue;
                }
                try {
                    Student student = new Student(
                            unescapeField(parts[0]),
                            unescapeField(parts[1]),
                            parts[2],
                            unescapeField(parts[3]),
                            unescapeField(parts[5]),
                            unescapeField(parts[6]),
                            library
                    );
                    student.setActive(Boolean.parseBoolean(parts[7]));
                    library.addStudent(student);
                    studentCount++;
                } catch (IllegalArgumentException e) {
                    System.err.println("Validation error loading student: " + e.getMessage());
                }
            }
            System.out.println("Loaded " + studentCount + " student(s) successfully.");
        } catch (FileNotFoundException e) {
            // File doesn't exist, continue with empty list for now
        } catch (IOException e) {
            System.err.println("Error loading students: " + e.getMessage());
        }
        return library;
    }
}