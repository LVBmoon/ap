package ap.exercises.exe3;

import java.time.Year;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main_EX3_LM_1_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Book book1 = new Book("Hunters of the Dusk", "Darren", "Shan", 357, 2002);
        Book book2 = new Book("Sons of Destiny", "Darren", "Shan", 318, 2004);
        Book book3 = new Book("The Hobbit", "J.R.R.", "Tolkien", 310, 1937);
        Book book4 = new Book("1984", "George", "Orwell", 328, 1949);

        Student student1 = new Student("Grubitsch", "Grady", "Magic", 404123456);
        Student student2 = new Student("Abraham", "Garadex", "philosophy", 400495139);
        Student student3 = new Student("Dervish", "Grady", "electrical engineering", 123456789);

        Book[] books = {book1, book2, book3, book4};
        Student[] students = {student1, student2, student3};

        CreateFiles.createFile();
        CreateFiles.writeBooksToFile(books);
        CreateFiles.writeStudentsToFile(students);


        Book[] loadedBooks = CreateFiles.readBooksFromFile();
        Student[] loadedStudents = CreateFiles.readStudentsFromFile();

        for (Book book : loadedBooks) {
            System.out.println(book.getTitle() + " - " + book.getAuthorName());
        }
        for (Student student : loadedStudents) {
            System.out.println(student.getFirstName() + " - " + student.getFieldOfStudy());
        }

        System.out.println("\nPlease enter the name of student you would like to search: ");
        String searchStudent = sc.nextLine();
        Student[] searchResults = CreateFiles.searchStudentsByName(loadedStudents, searchStudent);

        if (searchResults.length == 0) {
            System.out.println("No student found with name " + searchStudent);
        }
        else {
            System.out.println("\nSearch Results:");
            for (Student student : searchResults) {
                System.out.println(student.getFirstName() + " " + student.getLastName());
            }
        }
    }
}

class Book {
    private String title;
    private String authorName;
    private String authorLastName;
    private int pageCount;
    private int publishedYear;

    public Book(String title, String authorName, String authorLastName, int pageCount, int publishedYear) {
        this.title = title;
        this.authorName = authorName;
        this.authorLastName = authorLastName;
        setPageCount(pageCount);
        setPublishedYear(publishedYear);
    }

    // Getters
    public String getTitle() {
        return title;
    }
    public String getAuthorName() {
        return authorName;
    }
    public String getAuthorLastName() {
        return authorLastName;
    }
    public int getPageCount() {
        return pageCount;
    }
    public int getPublishedYear() {
        return publishedYear;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public void setPageCount(int pageCount) {
        if(pageCount > 0) this.pageCount = pageCount;
        else System.out.println("The number of pages must be greater than 0");
    }

    public void setPublishedYear(int publishedYear) {
        if(publishedYear > 0 && publishedYear <= Year.now().getValue())
            this.publishedYear = publishedYear;
        else
            System.out.println("Invalid published year.");
    }

    @Override
    public String toString() {
        return String.join(",",
                title,
                authorName,
                authorLastName,
                String.valueOf(pageCount),
                String.valueOf(publishedYear)
        );
    }

    public static Book fromString(String line) {
        String[] parts = line.split(",");
        return new Book(
                parts[0].trim(),
                parts[1].trim(),
                parts[2].trim(),
                Integer.parseInt(parts[3].trim()),
                Integer.parseInt(parts[4].trim())
        );
    }
}

class Student {
    private String firstName;
    private String lastName;
    private String fieldOfStudy;
    private int studentID;

    public Student(String firstName, String lastName, String fieldOfStudy, int studentID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fieldOfStudy = fieldOfStudy;
        setStudentID(studentID);
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getFieldOfStudy() {
        return fieldOfStudy;
    }
    public int getStudentID() {
        return studentID;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public void setStudentID(int studentID) {
        if(studentID >= 111111111 && studentID <= 999999999)
            this.studentID = studentID;
        else
            System.out.println("Invalid Student ID! Must have 9 digits.");
    }

    @Override
    public String toString() {
        return String.join(",",
                firstName,
                lastName,
                fieldOfStudy,
                String.valueOf(studentID)
        );
    }

    //part B:
    public static Student fromString(String line) {
        String[] parts = line.split(",");
        return new Student(
                parts[0].trim(),
                parts[1].trim(),
                parts[2].trim(),
                Integer.parseInt(parts[3].trim())
        );
    }
}

class CreateFiles {
    public static void createFile() {
        try {
            File booksFile = new File("BooksFile.txt");
            if(booksFile.createNewFile())
                System.out.println("File created : " + booksFile.getName());
            else
                System.out.println("File already exists.");

            File studentsFile = new File("StudentFile.txt");
            if(studentsFile.createNewFile())
                System.out.println("File created : " + studentsFile.getName());
            else
                System.out.println("File already exists.");

        } catch(IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void writeBooksToFile(Book[] books) {
        try(FileWriter writer = new FileWriter("BooksFile.txt")) {
            for(Book book : books) {
                writer.write(book.toString() + "\n");
            }
            System.out.println("Successfully wrote books to the file.");
        } catch(IOException e) {
            System.out.println("An error occurred while trying to write the books to the file.");
            e.printStackTrace();
        }
    }

    public static void writeStudentsToFile(Student[] students) {
        try(FileWriter writer = new FileWriter("StudentFile.txt")) {
            for(Student student : students) {
                writer.write(student.toString() + "\n");
            }
            System.out.println("Successfully wrote students to the file.");
        } catch(IOException e) {
            System.out.println("An error occurred while trying to write the books to the file.");
            e.printStackTrace();
        }
    }

    public static Book[] readBooksFromFile() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("BooksFile.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    books.add(Book.fromString(line));
                } catch (Exception e) {
                    System.out.println("Invalid book data: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading books file!");
        }
        return books.toArray(new Book[0]);
    }

    public static Student[] readStudentsFromFile() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("StudentFile.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    students.add(Student.fromString(line));
                } catch (Exception e) {
                    System.out.println("Invalid student data: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading students file!");
        }
        return students.toArray(new Student[0]);
    }

    public static Student[] searchStudentsByName(Student[] students, String searchTerm) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getFirstName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    student.getLastName().toLowerCase().contains(searchTerm.toLowerCase())) {
                result.add(student);
            }
        }
        return result.toArray(new Student[0]);
    }
}
