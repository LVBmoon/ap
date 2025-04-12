
package ap.exercises.exe3;

import java.time.Year;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class Main_EX3_LM_2_1 {
    private static List<BorrowRecord> borrowRecords = new ArrayList<>();
    private static Book[] loadedBooks;
    private static Student[] loadedStudents;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
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

        Main_EX3_LM_2_1.loadedBooks = CreateFiles.readBooksFromFile();
        Main_EX3_LM_2_1.loadedStudents = CreateFiles.readStudentsFromFile();
        borrowRecords = CreateFiles.readBorrowRecords();

        showMainMenu();

    }

    private static void showMainMenu() {
        boolean flag = true;
        System.out.println("\n*** Welcome to Library management system ***");
        while (flag) {
            System.out.println("MENU: \n1. Books features \n2. Students features \n3. Exit \nEnter your choice:");

            switch (sc.nextLine().trim()) {
                case "1" :
                    handleBooksMenu();
                    break;
                case "2" :
                    handleStudentsMenu();
                    break;
                case "3" :
                    System.out.println("\nExiting...");
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void handleBooksMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Books Features ---\n   1. Add book\n   2. Search book\n   3. barrow book\n   \nreturn book" +
                    "\n   5. Back to main menu\n   Enter your choice:");
            switch (sc.nextLine().trim()) {
                case "1":
                    addNewBook();
                    break;
                case "2" :
                    searchBooks();
                    break;
                case "3":
                    borrowBook();
                    break;
                case "4":
                    returnBook();
                    break;
                case "5" :
                    back = true;
                    break;
                default :
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }

    private static void handleStudentsMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Students Features ---\n   1. Add student \n   2. Search student \n   3. Back to main menu \n   Enter your choice:");
            switch (sc.nextLine().trim()) {
                case "1" :
                    addNewStudent();
                    break;
                case "2" :
                    searchStudents();
                    break;
                case "3" :
                    back = true;
                    break;
                default :
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }
    private static void addNewBook() {
        try {
            System.out.print("Enter title: ");
            String title = sc.nextLine();

            System.out.print("Enter author's first name: ");
            String authorName = sc.nextLine();

            System.out.print("Enter author's last name: ");
            String authorLastName = sc.nextLine();

            System.out.print("Enter page count: ");
            int pages = Integer.parseInt(sc.nextLine());

            System.out.print("Enter publication year: ");
            int year = Integer.parseInt(sc.nextLine());

            Book newBook = new Book(title, authorName, authorLastName, pages, year);
            CreateFiles.appendBookToFile(newBook);
            loadedBooks = CreateFiles.readBooksFromFile();

            System.out.println("Book added successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format!");
        }
    }
    private static void searchBooks() {
        System.out.print("Enter search term (title/author): ");
        String term = sc.nextLine().toLowerCase();

        List<Book> results = new ArrayList<>();
        for (Book book : loadedBooks) {
            if (book.getTitle().toLowerCase().contains(term) ||
                    book.getAuthorName().toLowerCase().contains(term) ||
                    book.getAuthorLastName().toLowerCase().contains(term)) {
                results.add(book);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("\nSearch Results:");
            results.forEach(b -> System.out.println(
                    b.getTitle() + " by " + b.getAuthorName() + " " + b.getAuthorLastName()
            ));
        }
    }

    private static void addNewStudent() {
        try {
            System.out.print("Enter first name: ");
            String firstName = sc.nextLine();

            System.out.print("Enter last name: ");
            String lastName = sc.nextLine();

            System.out.print("Enter field of study: ");
            String field = sc.nextLine();

            System.out.print("Enter student ID (9 digits): ");
            int id = Integer.parseInt(sc.nextLine());

            Student newStudent = new Student(firstName, lastName, field, id);
            CreateFiles.appendStudentToFile(newStudent);
            loadedStudents = CreateFiles.readStudentsFromFile();

            System.out.println("Student added successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format!");
        }
    }

    private static void searchStudents() {
        System.out.print("Enter student name to search: ");
        String term = sc.nextLine();
        Student[] results = CreateFiles.searchStudentsByName(loadedStudents, term);

        if (results.length == 0) {
            System.out.println("No students found.");
        } else {
            System.out.println("\nSearch Results:");
            for (Student s : results) {
                System.out.println(s.getFirstName() + " " + s.getLastName());
            }
        }
    }

    private static void borrowBook() {
        System.out.print("Enter student ID: ");
        int studentId = Integer.parseInt(sc.nextLine());
        System.out.print("Enter book title: ");
        String title = sc.nextLine();

        BorrowRecord record = new BorrowRecord(studentId, title);
        CreateFiles.saveBorrowRecord(record);
        borrowRecords.add(record);
        System.out.println("Book borrowed successfully!");
    }

    private static void returnBook() {
        System.out.print("Enter student ID: ");
        int studentId = Integer.parseInt(sc.nextLine());
        System.out.print("Enter book title: ");
        String title = sc.nextLine();

        for (BorrowRecord record : borrowRecords) {
            if (record.getStudentId() == studentId &&
                    record.getBookTitle().equalsIgnoreCase(title) &&
                    record.getReturnDate() == null) {

                record.setReturnDate();
                CreateFiles.updateBorrowRecords(borrowRecords);
                System.out.println("Book returned successfully!");
                return;
            }
        }
        System.out.println("No active borrow record found!");
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

class BorrowRecord {
    private int studentId;
    private String bookTitle;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowRecord(int studentId, String bookTitle) {
        this.studentId = studentId;
        this.bookTitle = bookTitle;
        this.borrowDate = LocalDate.now();
        this.returnDate = null;
    }

    // Getters & Setters
    public void setReturnDate() {
        this.returnDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return String.join(",",
                String.valueOf(studentId),
                bookTitle,
                borrowDate.toString(),
                returnDate != null ? returnDate.toString() : "null"
        );
    }

    public static BorrowRecord fromString(String line) {
        String[] parts = line.split(",");
        BorrowRecord record = new BorrowRecord(
                Integer.parseInt(parts[0]),
                parts[1]
        );
        record.borrowDate = LocalDate.parse(parts[2]);
        if (!parts[3].equals("null")) {
            record.returnDate = LocalDate.parse(parts[3]);
        }
        return record;
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

    public static void appendBookToFile(Book book) {
        try (FileWriter writer = new FileWriter("BooksFile.txt", true)) {
            writer.write(book.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Error appending book to file!");
        }
    }

    public static void appendStudentToFile(Student student) {
        try (FileWriter writer = new FileWriter("StudentFile.txt", true)) {
            writer.write(student.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Error appending student to file!");
        }
    }

    public static void saveBorrowRecord(BorrowRecord record) {
        try (FileWriter writer = new FileWriter("BorrowRecords.txt", true)) {
            writer.write(record.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Error saving borrow record!");
        }
    }

    public static List<BorrowRecord> readBorrowRecords() {
        List<BorrowRecord> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("BorrowRecords.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(BorrowRecord.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Error reading borrow records!");
        }
        return records;
    }

    public static void updateBorrowRecords(List<BorrowRecord> records) {
        try (FileWriter writer = new FileWriter("BorrowRecords.txt")) {
            for (BorrowRecord record : records) {
                writer.write(record.toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error updating borrow records!");
        }
    }
}
