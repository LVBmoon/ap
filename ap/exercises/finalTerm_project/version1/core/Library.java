package ap.exercises.finalTerm_project.version1.core;

import ap.exercises.finalTerm_project.version1.model.Student;
import java.util.ArrayList;

public class Library {
    private String name;
    private ArrayList<Student> students;

    public Library(String name) {
        setName(name);
        this.students = new ArrayList<>();
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

    public boolean addStudent(Student student) {
        return students.add(student);
    }

    public Student findStudentByStudentId(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name + " Library - Students: " + students.size();
    }
}