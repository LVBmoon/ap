package ap.exercises.finalTerm_project.version1.menu;

import ap.exercises.finalTerm_project.version1.core.Library;
import ap.exercises.finalTerm_project.version1.data.DataStorage;
import ap.exercises.finalTerm_project.version1.data.InputProcessor;
import ap.exercises.finalTerm_project.version1.model.Book;
import ap.exercises.finalTerm_project.version1.model.Librarian;
import ap.exercises.finalTerm_project.version1.core.Borrow;
import ap.exercises.finalTerm_project.version1.core.BorrowRequest;
import ap.exercises.finalTerm_project.version1.model.Student;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibrarianMenu extends Menu {
    private Librarian librarian;

    public LibrarianMenu(Library library, InputProcessor inputProcessor) {
        super(library, inputProcessor);
        this.librarian = null;
    }
    @Override
    public void showMenu() {
        while (true) {
            if (librarian == null) {
                System.out.println("\n=== Librarian Menu ===");
                System.out.println("   1. Login");
                System.out.println("   2. Exit");
                int choice = inputProcessor.getIntInput("Please enter choice: ");
                switch (choice) {
                    case 1:
                        login();
                        break;
                    case 2:
                        return;
                    default:
                        System.out.println("\nInvalid choice!");
                }
            } else {
                System.out.println("\n--- Librarian Menu ---\nWelcome " + librarian.getFirstName() + " " + librarian.getLastName() + "!");
                System.out.println("   1. Edit Personal Info");
                System.out.println("   2. Add New Book");
                System.out.println("   3. Edit Book");
                System.out.println("   4. Review Borrow Requests");
                System.out.println("   5. Review Return Requests");
                System.out.println("   6. View Processed Borrows");
                System.out.println("   7. View Student Borrow History");
                System.out.println("   8. Change Password");
                System.out.println("   9. Toggle Student Active Status");
                System.out.println("   10. Logout");
                int choice = inputProcessor.getIntInput("Please enter choice: ");
                switch (choice) {
                    case 1:
                        editPersonalInfo();
                        break;
                    case 2:
                        addNewBook();
                        break;
                    case 3:
                        editBook();
                        break;
                    case 4:
                        reviewBorrowRequests();
                        break;
                    case 5:
                        reviewReturnRequests();
                        break;
                    case 6:
                        viewProcessedBorrows();
                        break;
                    case 7:
                        viewStudentHistory();
                        break;
                    case 8:
                        changePassword();
                        break;
                    case 9:
                        toggleStudentActive();
                        break;
                    case 10:
                        librarian = null;
                        break;
                    default:
                        System.out.println("\nInvalid choice!");
                }
            }
            new DataStorage().saveLibrary(library);
        }
    }

    private void login() {
        String username = inputProcessor.getStringInput("Enter username: ");
        String password = inputProcessor.getStringInput("Enter password: ");
        for (Librarian l : library.getLibrarians()) {
            if (l.getUsername().equals(username) && l.checkPassword(password)) {
                librarian = l;
                System.out.println("\nLogin successful.");
                return;
            }
        }
        System.out.println("\nInvalid username or password!");
    }

    private void changePassword() {
        String currentPass = inputProcessor.getStringInput("Enter current password: ");
        if (!librarian.checkPassword(currentPass)) {
            System.out.println("\nIncorrect current password!");
            return;
        }
        String newPass = inputProcessor.getStringInput("Enter new password: ");
        try {
            librarian.setPassword(newPass);
            System.out.println("\nPassword changed successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("\nPassword change failed: " + e.getMessage());
        }
    }

    private void editPersonalInfo() {
        String phoneNumber = inputProcessor.getStringInput("Enter phone number (e.g. +989123456789, or press enter to skip): ");
        String address = inputProcessor.getStringInput("Enter address (or press enter to skip): ");
        String educationLevel = inputProcessor.getStringInput("Enter education level (e.g. BSc, or press enter to skip): ");
        try {
            librarian.setPhoneNumber(phoneNumber);
            librarian.setAddress(address);
            if (!educationLevel.isEmpty()) {
                librarian.setEducationLevel(educationLevel);
            }
            System.out.println("\nPersonal info updated.");
        } catch (IllegalArgumentException e) {
            System.out.println("\nFailed to update info: " + e.getMessage());
        }
    }

    private void addNewBook() {
        String bookId = inputProcessor.getStringInput("Enter book ID: ");
        if (library.findBookByBookId(bookId) != null) {
            System.out.println("Book ID already exists!");
            return;
        }
        String title = inputProcessor.getStringInput("Enter book title: ");
        String author = inputProcessor.getStringInput("Enter author: ");
        int year = inputProcessor.getIntInput("Enter publication year: ");
        int pages = inputProcessor.getIntInput("Enter number of pages: ");
        try {
            Book book = new Book(bookId, title, author, year, pages);
            library.addBook(book);
            librarian.incrementRegisteredBooks();
            System.out.println("\nBook added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("\nFailed to add book: " + e.getMessage());
        }
    }

    private void editBook() {
        String bookId = inputProcessor.getStringInput("Enter book ID to edit: ");
        Book book = library.findBookByBookId(bookId);
        if (book == null) {
            System.out.println("No book found with that ID.");
            return;
        }
        System.out.println("Current Book Details: " + book);
        String title = inputProcessor.getStringInput("Enter new title (or press enter to keep current): ");
        String author = inputProcessor.getStringInput("Enter new author (or press enter to keep current): ");
        String yearStr = inputProcessor.getStringInput("Enter new publication year (or press enter to keep current): ");
        String pagesStr = inputProcessor.getStringInput("Enter new page count (or press enter to keep current): ");
        try {
            if (!title.isEmpty()) book.setTitle(title);
            if (!author.isEmpty()) book.setAuthor(author);
            if (!yearStr.isEmpty()) book.setPublicationYear(Integer.parseInt(yearStr));
            if (!pagesStr.isEmpty()) book.setPageCount(Integer.parseInt(pagesStr));
            System.out.println("Book updated successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to update book: " + e.getMessage());
        }
    }

    private void reviewBorrowRequests() {
        List<BorrowRequest> filteredRequests = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        for (BorrowRequest request : library.getBorrowRequests()) {
            if (request.getAssignedLibrarian().getEmployeeId().equals(librarian.getEmployeeId()) &&
                    request.getStatus() == BorrowRequest.RequestStatus.PENDING && !request.isReturnRequest() &&
                    (request.getRequestDate().equals(today) || request.getRequestDate().equals(yesterday))) {
                filteredRequests.add(request);
            }
        }

        if (filteredRequests.isEmpty()) {
            System.out.println("No pending borrow requests for today or yesterday.");
            return;
        }

        System.out.println("\nPending Borrow Requests:");
        for (int i = 0; i < filteredRequests.size(); i++) {
            System.out.println((i + 1) + ". " + filteredRequests.get(i));
        }

        int choice = inputProcessor.getIntInput("Enter request number to process (or 0 to cancel): ");
        if (choice == 0) {
            return;
        }
        if (choice < 1 || choice > filteredRequests.size()) {
            System.out.println("Invalid request number!");
            return;
        }

        BorrowRequest selectedRequest = filteredRequests.get(choice - 1);
        System.out.println("\nRequest Details: " + selectedRequest);
        int action = inputProcessor.getIntInput("1. Approve\n2. Reject\nEnter choice: ");
        if (action == 1) {
            if (selectedRequest.getStudent().borrowBook(selectedRequest.getBook())) {
                Borrow borrow = new Borrow(selectedRequest.getBook(), selectedRequest.getStudent(),
                        librarian, LocalDate.now());
                library.addBorrow(borrow);
                selectedRequest.approve();
                librarian.incrementProcessedLoans();
                librarian.incrementBorrowsGiven();
                System.out.println("Borrow request approved.");
            } else {
                selectedRequest.reject();
                System.out.println("Borrow request rejected: Book not available or student limit reached.");
            }
        } else if (action == 2) {
            selectedRequest.reject();
            System.out.println("Borrow request rejected.");
        } else {
            System.out.println("Invalid action!");
        }
    }
    private void viewStudentHistory() {
        String studentId = inputProcessor.getStringInput("Enter student ID: ");
        Student student = library.findStudentByStudentId(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        boolean found = false;
        int totalBorrows = 0;
        int notReturned = 0;
        int delayed = 0;
        System.out.println("Borrow History for " + student.getFirstName() + " " + student.getLastName() + ":");
        for (Borrow borrow : library.getBorrows()) {
            if (borrow.getStudent().getStudentId().equals(studentId)) {
                System.out.println(borrow);
                totalBorrows++;
                if (!borrow.isReturned()) {
                    notReturned++;
                }
                if (borrow.isOverdue()) {
                    delayed++;
                }
                found = true;
            }
        }
        if (!found) {
            System.out.println("\nNo borrow history for this student.");
        } else {
            System.out.println("\nStatistics:");
            System.out.println("Total Borrows: " + totalBorrows);
            System.out.println("Not Returned: " + notReturned);
            System.out.println("Delayed Returns: " + delayed);
        }
    }

    private void toggleStudentActive() {
        String studentId = inputProcessor.getStringInput("Enter student ID: ");
        Student student = library.findStudentByStudentId(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        student.setActive(!student.isActive());
        System.out.println("Student " + student.getFirstName() + " " + student.getLastName() +
                " is now " + (student.isActive() ? "active" : "inactive") + ".");
    }

    private void reviewReturnRequests() {
        List<BorrowRequest> filteredRequests = new ArrayList<>();
        for (BorrowRequest request : library.getBorrowRequests()) {
            if (request.getAssignedLibrarian().getEmployeeId().equals(librarian.getEmployeeId()) &&
                    request.getStatus() == BorrowRequest.RequestStatus.PENDING && request.isReturnRequest()) {
                filteredRequests.add(request);
            }
        }

        if (filteredRequests.isEmpty()) {
            System.out.println("No pending return requests.");
            return;
        }

        System.out.println("\nPending Return Requests:");
        for (int i = 0; i < filteredRequests.size(); i++) {
            BorrowRequest request = filteredRequests.get(i);
            Borrow borrow = null;
            for (Borrow b : library.getBorrows()) {
                if (b.getBook().getBookId().equalsIgnoreCase(request.getBook().getBookId()) &&
                        b.getStudent().getStudentId().equals(request.getStudent().getStudentId()) &&
                        !b.isReturned()) {
                    borrow = b;
                    break;
                }
            }
            String borrowInfo = borrow != null ? ", Borrow Date: " + borrow.getBorrowDate() +
                    ", Due Date: " + borrow.getDueDate() : "";
            System.out.println((i + 1) + ". " + request + ", Request Date: " +
                    request.getRequestDate() + borrowInfo);
        }

        int choice = inputProcessor.getIntInput("Enter request number to process (or 0 to cancel): ");
        if (choice == 0) {
            return;
        }
        if (choice < 1 || choice > filteredRequests.size()) {
            System.out.println("Invalid request number!");
            return;
        }

        BorrowRequest selectedRequest = filteredRequests.get(choice - 1);
        Borrow borrow = null;
        for (Borrow b : library.getBorrows()) {
            if (b.getBook().getBookId().equalsIgnoreCase(selectedRequest.getBook().getBookId()) &&
                    b.getStudent().getStudentId().equals(selectedRequest.getStudent().getStudentId()) &&
                    !b.isReturned()) {
                borrow = b;
                break;
            }
        }
        if (borrow == null) {
            System.out.println("No active borrow found for this request!");
            return;
        }
        System.out.println("\nRequest Details: " + selectedRequest +
                ", Borrow Date: " + borrow.getBorrowDate() +
                ", Due Date: " + borrow.getDueDate());
        int action = inputProcessor.getIntInput("1. Approve\n2. Reject\nEnter choice: ");
        if (action == 1) {
            if (selectedRequest.getStudent().returnBook(selectedRequest.getBook())) {
                borrow.returnBook(librarian, LocalDate.now());
                selectedRequest.approve();
                librarian.incrementProcessedLoans();
                librarian.incrementBorrowsReceived();
                System.out.println("Return request approved.");
            } else {
                selectedRequest.reject();
                System.out.println("Return request rejected: Book not in student's borrowed list.");
            }
        } else if (action == 2) {
            selectedRequest.reject();
            System.out.println("Return request rejected.");
        } else {
            System.out.println("Invalid action!");
        }
    }

    private void viewProcessedBorrows() {
        boolean found = false;
        System.out.println("Processed Borrows:");
        for (Borrow borrow : library.getBorrows()) {
            if (borrow.getIssuedBy().getEmployeeId().equals(librarian.getEmployeeId()) ||
                    (borrow.isReturned() && borrow.getReceivedBy() != null &&
                            borrow.getReceivedBy().getEmployeeId().equals(librarian.getEmployeeId()))) {
                System.out.println(borrow);
                found = true;
            }
        }
        if (!found) {
            System.out.println("\nNo borrows processed by you!");
        }
    }
}

