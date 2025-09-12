package ap.exercises.finalTerm_project.version2.service;
//---
import ap.exercises.finalTerm_project.version2.core.Borrow;
import ap.exercises.finalTerm_project.version2.core.Library;
import ap.exercises.finalTerm_project.version2.core.BorrowRequest;
import ap.exercises.finalTerm_project.version2.data.InputProcessor;
import ap.exercises.finalTerm_project.version2.model.Book;
import ap.exercises.finalTerm_project.version2.model.Librarian;
import ap.exercises.finalTerm_project.version2.model.Student;
//---
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class StudentService extends BaseService {
    private Student student;

    public StudentService(Library library, InputProcessor inputProcessor) {
        super(library, inputProcessor);
    }

    public void register() {
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
        // لوپ username بعداً HashMap
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

    public void login() {
        if (library.getStudents().isEmpty()) {
            System.out.println("No students loaded. Please register or check data files (students.txt).");
            return;
        }
        String username = inputProcessor.getStringInput("Enter username: ");
        String password = inputProcessor.getStringInput("Enter password: ");
        for (Student s : library.getStudents()) {
            if (s.getUsername().equals(username) && s.checkPassword(password)) {
                this.student = s;
                System.out.println("Login successful.");
                return;
            }
        }
        System.out.println("Invalid username or password.");
    }

    public Student getStudent() {
        return student;
    }

    public boolean isLoggedIn() {
        return student != null;
    }

    public void searchBooks() {
        String title = inputProcessor.getStringInput("Enter book title (*or press enter to skip): ");
        String yearStr = inputProcessor.getStringInput("Enter publication year (*or press enter to skip): ");
        Integer year = yearStr.isEmpty() ? null : Integer.parseInt(yearStr);
        String author = inputProcessor.getStringInput("Enter author (or press enter to skip): ");
        List<Book> results = library.searchBooks(title.isEmpty() ? null : title, year, author.isEmpty() ? null : author);
        if (results.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("Search Results:");
            for (Book book : results) {
                System.out.println(book);
            }
        }
    }

    public void requestBorrow() {
        if (!isLoggedIn()) {
            System.out.println("Please login first.");
            return;
        }
        String bookId = inputProcessor.getStringInput("Enter book ID to borrow: ");
        Book book = library.findBookByBookId(bookId);
        if (book == null) {
            System.out.println("No book found with that ID.");
            return;
        }
        if (!student.isActive()) {
            System.out.println("Your account is deactivated and cannot borrow books.");
            return;
        }
        if (student.getBorrowedBooks().size() >= 5) {
            System.out.println("You have reached the maximum borrow limit (5 books).");
            return;
        }
        if (!book.isAvailable()) {
            System.out.println("Book is not available.");
            return;
        }
        Librarian librarian = library.getRandomLibrarian();
        BorrowRequest request = new BorrowRequest(book, student, librarian, LocalDate.now(), LocalDate.now().plusDays(14), false);
        library.addBorrowRequest(request);
        System.out.println("Borrow request sent to " + librarian.getFirstName() + " " + librarian.getLastName() + ".");
    }

    public void requestReturn() {
        if (!isLoggedIn()) {
            System.out.println("Please login first.");
            return;
        }
        String bookId = inputProcessor.getStringInput("Enter book ID to return: ");
        Borrow borrow = library.getBorrows().stream()
                .filter(b -> b.getBook().getBookId().equalsIgnoreCase(bookId)
                        && b.getStudent().getStudentId().equals(student.getStudentId())
                        && !b.isReturned())
                .findFirst().orElse(null);
        if (borrow == null) {
            System.out.println("No active borrow found for this book!");
            return;
        }
        Librarian librarian = library.getRandomLibrarian();
        BorrowRequest request = new BorrowRequest(borrow.getBook(), student, librarian, LocalDate.now(), borrow.getDueDate(), true);
        library.addBorrowRequest(request);
        System.out.println("Return request sent to " + librarian.getFirstName() + " " + librarian.getLastName() + ".");
    }

    public void viewRequestStatus() {
        if (!isLoggedIn()) {
            System.out.println("Please login first.");
            return;
        }
        boolean found = false;
        System.out.println("Your Request Status:");
        List<BorrowRequest> studentRequests = library.getBorrowRequests().stream()
                .filter(request -> request.getStudent().getStudentId().equals(student.getStudentId()))
                .collect(Collectors.toList());
        for (BorrowRequest request : studentRequests) {
            System.out.println(request);
            found = true;
        }
        if (!found) {
            System.out.println("No requests found.");
        }
    }

    public void viewActiveBorrows() {
        if (!isLoggedIn()) {
            System.out.println("Please login first.");
            return;
        }
        boolean found = false;
        System.out.println("Active Borrows:");
        for (Borrow borrow : library.getBorrows()) {
            if (borrow.getStudent().getStudentId().equals(student.getStudentId()) && !borrow.isReturned()) {
                System.out.println(borrow);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No active borrows.");
        }
    }

    public void viewBorrowHistory() {
        if (!isLoggedIn()) {
            System.out.println("Please login first.");
            return;
        }
        boolean found = false;
        System.out.println("Borrow History:");
        List<Borrow> studentBorrows = library.getBorrows().stream()
                .filter(borrow -> borrow.getStudent().getStudentId().equals(student.getStudentId()))
                .collect(Collectors.toList());
        for (Borrow borrow : studentBorrows) {
            System.out.println(borrow);
            found = true;
        }
        if (!found) {
            System.out.println("No borrow history.");
        }
    }

    public void changePassword() {
        if (!isLoggedIn()) {
            System.out.println("Please login first.");
            return;
        }
        String currentPass = inputProcessor.getStringInput("Enter current password: ");
        if (!student.checkPassword(currentPass)) {
            System.out.println("Incorrect current password!");
            return;
        }
        String newPass = inputProcessor.getStringInput("Enter new password: ");
        try {
            student.setPassword(newPass);
            System.out.println("Password changed successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Password change failed: " + e.getMessage());
        }
    }
}