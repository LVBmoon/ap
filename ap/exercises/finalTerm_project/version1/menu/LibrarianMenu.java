package ap.exercises.finalTerm_project.version1.menu;

import src.selfTraining.version0.core.Library;
import src.selfTraining.version0.data.DataStorage;
import src.selfTraining.version0.data.InputProcessor;
import src.selfTraining.version0.menu.Menu;
import src.selfTraining.version0.model.Book;
import src.selfTraining.version0.model.Librarian;

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
                    case 8:
                        changePassword();

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

}

