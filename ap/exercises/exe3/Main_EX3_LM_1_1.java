package ap.exercises.exe3;

import java.time.Year;

public class Main_EX3_LM_1_1 {
    public static void main(String[] args) {
        Book book1 = new Book("Hunters of the Dusk", "Darren", "Shan", 357, 2002);
        Book book2 = new Book("Sons of Destiny", "Darren", "Shan", 318, 2004);

        Student student1 = new Student("Grubitsch", "Grady", "Magic", 123456789);
        Student student2 = new Student("Abraham", "Garadex", "philosophy", 400495139);

        System.out.println("=== Books ===");
        System.out.println("Book 1:\n1. Title : " + book1.getTitle() + "\n2. Author : " + book1.getAuthorName() + " " + book1.getAuthorLastName() +
                "\n3. published in : " + book1.getPublishedYear() + "\n4. pages : " + book1.getPageCount());
        System.out.println("Book 2:\n1. Title : " + book2.getTitle() + "\n2. Author : " + book2.getAuthorName() + " " + book2.getAuthorLastName() +
                "\n3. Published in : " + book2.getPublishedYear() + "\n4. Pages : " + book2.getPageCount());

        System.out.println("\n=== Students ===");
        System.out.println("Student 1:\n1. First name : " + student1.getFirstName() + "\n2. Last name : " + student1.getLastName() + "\n3. Field of study: "
                + student1.getFieldOfStudy() + "\n4. ID : " + student1.getStudentID());
        System.out.println("Student 1:\n1. First name : " + student2.getFirstName() + "\n2. Last name : " + student2.getLastName() + "\n3. Field of study: "
                + student2.getFieldOfStudy() + "\n4. ID : " + student2.getStudentID());
    }
}

    class Book {
    private String title;
    private String authorName;
    private String authorLastName;
    private int pageCount;
    private int publishedYear;

    public Book(String title, String authorName, String authorLastName, int pageCount, int publishYear) {
        this.title = title;
        this.authorName = authorName;
        this.authorLastName = authorLastName;
        this.pageCount = pageCount;
        this.publishedYear = publishYear;
    }

    // title getter and setter
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    // Author Name getter and setter
    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    // Author lastname getter and setter
    public String getAuthorLastName() {
        return authorLastName;
    }
    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    // PageCount getter and setter
    public int getPageCount() {
        return pageCount;
    }
    public void setPageCount(int pageCount) {
        if(pageCount > 0){
            this.pageCount = pageCount;
        }
        else{
            System.out.println("Invalid. Page count must be positive!");
        }
    }

    // Publish year getter and setter
    public int getPublishedYear() {
        return publishedYear;
    }
    public void setPublishedYear(int publishedYear) {
        if(publishedYear > 0 && publishedYear <= Year.now().getValue()) {
            this.publishedYear = publishedYear;
        }
        else{
            System.out.println("Invalid. Published year must be between 1 and the current year!");
        }
    }
}

class Student {
    private String firstName;
    private String lastName;
    private String fieldOfStudy; //major
    private int studentID;

    public Student(String firstName, String lastName, String fieldOfStudy, int studentID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fieldOfStudy = fieldOfStudy;
        this.studentID = studentID;
    }

    // Student first name getter and setter
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Student last name getter and setter
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Student field of study getter and setter
    public String getFieldOfStudy() {
        return fieldOfStudy;
    }
    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    // Student ID getter and setter
    public int getStudentID() {
        return studentID;
    }
    public void setStudentID(int studentID) {
        if(studentID >= 111111111 && studentID <= 999999999) {
            this.studentID = studentID;
        }
        else {
            System.out.println("Invalid Student ID! Must have 9 digits.");
        }
    }
}
