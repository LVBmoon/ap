package ap.exercises.midTerm_project.version2_part2;

import java.time.LocalDate;

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
                        System.out.println("\nInvalid choice! Try again.");
                }
            } else {
                System.out.println("\n--- Librarian Menu ---\nWelcome " + librarian.getFirstName() + " " + librarian.getLastName() + " !");
                System.out.println("   1. Edit Personal Info");
                System.out.println("   2. Add New Book");
                System.out.println("   3. Review Borrow Requests");
                System.out.println("   4. Review Return Requests");
                System.out.println("   5. View Processed Borrows");
                System.out.println("   6. View Student Borrow History");
                System.out.println("   7. Change Password");
                System.out.println("   8. Logout");
                int choice = inputProcessor.getIntInput("Please enter choice: ");
                switch (choice) {
                    case 1:
                        editPersonalInfo();
                        break;
                    case 2:
                        addNewBook();
                        break;
                    case 3:
                        reviewBorrowRequests();
                        break;
                    case 4:
                        reviewReturnRequests();
                        break;
                    case 5:
                        viewProcessedBorrows();
                        break;
                    case 6:
                        viewStudentHistory();
                        break;
                    case 7:
                        changePassword();
                        break;
                    case 8:
                        librarian = null;
                        break;
                    default:
                        System.out.println("\nInvalid choice!");
                }
            }
        }
    }

    private void login() {
        String username = inputProcessor.getStringInput("Enter username: ");
        String password = inputProcessor.getStringInput("Enter password: ");
        for (int i = 0; i < library.getLibrarians().size(); i++) {
            Librarian l = library.getLibrarians().get(i);
            if (l.getUsername().equals(username) && l.checkPassword(password)) {
                librarian = l;
                System.out.println("\nLogin successful.");
                return;
            }
        }
        System.out.println("\nInvalid username or password!");
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
        String title = inputProcessor.getStringInput("Enter book title: ");
        String author = inputProcessor.getStringInput("Enter author: ");
        int year = inputProcessor.getIntInput("Enter publication year: ");
        int pages = inputProcessor.getIntInput("Enter number of pages: ");
        try {
            Book book = new Book(title, author, year, pages);
            library.addBook(book);
            System.out.println("\nBook added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("\nFailed to add book: " + e.getMessage());
        }
    }

    private void reviewBorrowRequests() {
        boolean found = false;
        System.out.println("\nPending Borrow Requests:");
        ArrayList<BorrowRequest> requests = library.getBorrowRequests();
        for (int i = 0; i < requests.size(); i++) {
            BorrowRequest request = requests.get(i);
            if (request.getAssignedLibrarian().getEmployeeId().equals(librarian.getEmployeeId()) &&
                    request.getStatus().equals("PENDING") && !request.isReturnRequest()) {
                System.out.println((i + 1) + ". " + request);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No pending borrow requests.");
            return;
        }
        int choice = inputProcessor.getIntInput("Enter request number to process (or 0 to cancel): ");
        if (choice == 0) {
            return;
        }
        if (choice < 1 || choice > requests.size()) {
            System.out.println("Invalid request number!");
            return;
        }
        BorrowRequest selectedRequest = null;
        int index = 0;
        for (BorrowRequest request : requests) {
            if (request.getAssignedLibrarian().getEmployeeId().equals(librarian.getEmployeeId()) &&
                    request.getStatus().equals("PENDING") && !request.isReturnRequest()) {
                index++;
                if (index == choice) {
                    selectedRequest = request;
                    break;
                }
            }
        }
        if (selectedRequest == null) {
            System.out.println("Invalid request!");
            return;
        }
        System.out.println("\nRequest Details: " + selectedRequest);
        int action = inputProcessor.getIntInput("1. Approve\n2. Reject\nEnter choice: ");
        if (action == 1) {
            if (selectedRequest.getStudent().borrowBook(selectedRequest.getBook())) {
                Borrow borrow = new Borrow(selectedRequest.getBook(), selectedRequest.getStudent(),
                        librarian, LocalDate.now());
                library.addBorrow(borrow);
                selectedRequest.approve();
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

    private void reviewReturnRequests() {
        boolean found = false;
        System.out.println("\nPending Return Requests:");
        ArrayList<BorrowRequest> requests = library.getBorrowRequests();
        for (int i = 0; i < requests.size(); i++) {
            BorrowRequest request = requests.get(i);
            if (request.getAssignedLibrarian().getEmployeeId().equals(librarian.getEmployeeId()) &&
                    request.getStatus().equals("PENDING") && request.isReturnRequest()) {
                // Find the borrow record to show borrow and due dates
                Borrow borrow = null;
                for (Borrow b : library.getBorrows()) {
                    if (b.getBook().getTitle().equalsIgnoreCase(request.getBook().getTitle()) &&
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
                found = true;
            }
        }
        if (!found) {
            System.out.println("No pending return requests.");
            return;
        }
        int choice = inputProcessor.getIntInput("Enter request number to process (or 0 to cancel): ");
        if (choice == 0) {
            return;
        }
        if (choice < 1 || choice > requests.size()) {
            System.out.println("Invalid request number!");
            return;
        }
        BorrowRequest selectedRequest = null;
        int index = 0;
        for (BorrowRequest request : requests) {
            if (request.getAssignedLibrarian().getEmployeeId().equals(librarian.getEmployeeId()) &&
                    request.getStatus().equals("PENDING") && request.isReturnRequest()) {
                index++;
                if (index == choice) {
                    selectedRequest = request;
                    break;
                }
            }
        }
        if (selectedRequest == null) {
            System.out.println("Invalid request!");
            return;
        }
        // Find the borrow record
        Borrow borrow = null;
        for (Borrow b : library.getBorrows()) {
            if (b.getBook().getTitle().equalsIgnoreCase(selectedRequest.getBook().getTitle()) &&
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
        for (int i = 0; i < library.getBorrows().size(); i++) {
            Borrow borrow = library.getBorrows().get(i);
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

    private void viewStudentHistory() {
        String studentId = inputProcessor.getStringInput("Enter student ID: ");
        Student student = library.findStudentByStudentId(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        boolean found = false;
        System.out.println("Borrow History for " + student.getFirstName() + " " + student.getLastName() + ":");
        for (int i = 0; i < library.getBorrows().size(); i++) {
            Borrow borrow = library.getBorrows().get(i);
            if (borrow.getStudent().getStudentId().equals(studentId)) {
                System.out.println(borrow);
                found = true;
            }
        }
        if (!found) {
            System.out.println("\nNo borrow history for this student.");
        }
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
}