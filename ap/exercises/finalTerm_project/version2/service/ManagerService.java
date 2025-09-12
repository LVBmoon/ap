package ap.exercises.finalTerm_project.version2.service;
//---
import ap.exercises.finalTerm_project.version2.core.Borrow;
import ap.exercises.finalTerm_project.version2.core.Library;
import ap.exercises.finalTerm_project.version2.data.InputProcessor;
import ap.exercises.finalTerm_project.version2.model.Book;
import ap.exercises.finalTerm_project.version2.model.Librarian;
import ap.exercises.finalTerm_project.version2.model.LibraryManager;
import ap.exercises.finalTerm_project.version2.model.Student;
//---
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


public class ManagerService extends BaseService {
    private LibraryManager manager;

    public ManagerService(Library library, InputProcessor inputProcessor) {
        super(library, inputProcessor);
    }

    public void login() {
        String username = inputProcessor.getStringInput("Enter username: ");
        String password = inputProcessor.getStringInput("Enter password: ");
        LibraryManager m = library.getManager();
        if (m.getUsername().equals(username) && m.checkPassword(password)) {
            this.manager = m;
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid username or password!");
        }
    }

    public LibraryManager getManager() {
        return manager;
    }

    public boolean isLoggedIn() {
        return manager != null;
    }

    public void addNewLibrarian() {
        if (!isLoggedIn()) {
            System.out.println("Please login first.");
            return;
        }
        String firstName = inputProcessor.getStringInput("Enter librarian first name: ");
        String lastName = inputProcessor.getStringInput("Enter librarian last name: ");
        String employeeId = inputProcessor.getStringInput("Enter employee ID: ");
        String username = inputProcessor.getStringInput("Enter username: ");
        String password = inputProcessor.getStringInput("Enter password: ");
        String educationLevel = inputProcessor.getStringInput("Enter education level (e.g., BSc): ");
        Librarian existing = library.findLibrarianByEmployeeId(employeeId);
        if (existing != null) {
            System.out.println("Employee ID already exists!");
            return;
        }
        // لوپ برای username check بعداً با HashMap بهینه می‌شه
        for (Librarian l : library.getLibrarians()) {
            if (l.getUsername().equals(username)) {
                System.out.println("Username already exists!");
                return;
            }
        }
        try {
            Librarian librarian = new Librarian(firstName, lastName, employeeId, username, password, educationLevel);
            library.addLibrarian(librarian);
            System.out.println("Librarian added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to add librarian: " + e.getMessage());
        }
    }

    public void changePassword() {
        if (!isLoggedIn()) {
            System.out.println("Please login first.");
            return;
        }
        String currentPass = inputProcessor.getStringInput("Enter current password: ");
        if (!manager.checkPassword(currentPass)) {
            System.out.println("Incorrect current password!");
            return;
        }
        String newPass = inputProcessor.getStringInput("Enter new password: ");
        try {
            manager.setPassword(newPass);
            System.out.println("Password changed successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Password change failed: " + e.getMessage());
        }
    }

    public void viewLibrarianStatistics() {
        if (!isLoggedIn()) {
            System.out.println("Please login first.");
            return;
        }
        System.out.println("Librarian Statistics:");
        for (Librarian librarian : library.getLibrarians()) {
            System.out.println(librarian.getFirstName() + " " + librarian.getLastName() +
                    ": Registered Books: " + librarian.getRegisteredBooks() +
                    ", Borrows Given: " + librarian.getBorrowsGiven() +
                    ", Borrows Received: " + librarian.getBorrowsReceived() +
                    ", Total Processed: " + librarian.getProcessedLoans());
        }
    }

    public void viewOverdueBorrows() {
        if (!isLoggedIn()) {
            System.out.println("Please login first.");
            return;
        }
        List<Borrow> overdue = library.getOverdueBorrows();
        if (overdue.isEmpty()) {
            System.out.println("No overdue borrows.");
        } else {
            System.out.println("Overdue Borrows:");
            for (Borrow borrow : overdue) {
                System.out.println(borrow);
            }
        }
    }

    public void viewBorrowStatistics() {
        if (!isLoggedIn()) {
            System.out.println("Please login first.");
            return;
        }
        System.out.println("Borrow Statistics:");
        System.out.println("Total Borrow Requests: " + library.getTotalBorrowRequests());
        System.out.println("Total Approved Borrows: " + library.getTotalApprovedBorrows());
        System.out.println("Average Borrow Days: " + String.format("%.2f", library.getAverageBorrowDays()));
    }

    public void viewMostBorrowedBooks() {
        if (!isLoggedIn()) {
            System.out.println("Please login first.");
            return;
        }
        List<Book> topBooks = library.getMostBorrowedBooks(10);
        if (topBooks.isEmpty()) {
            System.out.println("No books borrowed yet!");
        } else {
            System.out.println("Top 10 Most Borrowed Books:");
            for (Book book : topBooks) {
                System.out.println(book + ", Borrow Count: " + book.getBorrowCount());
            }
        }
    }

    public void viewTopDelayedStudents() {
        if (!isLoggedIn()) {
            System.out.println("Please login first.");
            return;
        }
        List<Student> topDelayed = library.getTopDelayedStudents(10);
        if (topDelayed.isEmpty()) {
            System.out.println("No delayed borrows found.");
        } else {
            System.out.println("Top 10 Students with Most Delay Days:");
            for (Student student : topDelayed) {
                long totalDelay = library.getBorrows().stream()
                        .filter(borrow -> borrow.getStudent().getStudentId().equals(student.getStudentId())
                                && borrow.isOverdue())
                        .mapToLong(borrow -> {
                            LocalDate endDate = borrow.isReturned() ? borrow.getReturnDate() : LocalDate.now();
                            return ChronoUnit.DAYS.between(borrow.getDueDate(), endDate);
                        }).sum();
                System.out.println(student.getFirstName() + " " + student.getLastName() +
                        " (" + student.getStudentId() + ") - Total Delay Days: " + totalDelay);
            }
        }
    }
}