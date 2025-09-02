package ap.exercises.finalTerm_project.version1.model;

import ap.exercises.finalTerm_project.version1.core.Library;
import java.time.LocalDate;

public class Student extends User {
    private String studentId;
    private String fieldOfStudy;
    private LocalDate joinDate;
    private String username;
    private String password;
    private boolean isActive;
    private Library library;

    public Student(String firstName, String lastName, String studentId, String fieldOfStudy, String username, String password, Library library) {
        super(firstName, lastName);
        setStudentId(studentId);
        setFieldOfStudy(fieldOfStudy);
        setUsername(username);
        setPassword(password);
        this.joinDate = LocalDate.now();
        this.isActive = true;
        this.library = library;
    }

    public void setStudentId(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be empty!");
        }
        this.studentId = studentId.trim();
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        if (fieldOfStudy == null || fieldOfStudy.trim().isEmpty()) {
            throw new IllegalArgumentException("Field of study cannot be empty!");
        }
        this.fieldOfStudy = fieldOfStudy.trim();
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty!");
        }
        this.username = username.trim();
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters!");
        }
        this.password = password;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return "Student: " + getFirstName() + " " + getLastName() + ", ID: " + studentId +
                ", Field: " + fieldOfStudy + ", Active: " + isActive;
    }
}