package ap.exercises.finalTerm_project.version1.menu;

import ap.exercises.finalTerm_project.version1.core.Library;
import ap.exercises.finalTerm_project.version1.data.DataStorage;
import ap.exercises.finalTerm_project.version1.data.InputProcessor;
import ap.exercises.finalTerm_project.version1.model.Book;
import java.util.List;

public class GuestMenu extends Menu {
    public GuestMenu(Library library, InputProcessor inputProcessor) {
        super(library, inputProcessor);
    }

    @Override
    public void showMenu() {
        while (true) {
            System.out.println("\n=== Guest Menu ===");
            System.out.println("   1. View Total Students");
            System.out.println("   2. Search Books by Title");
            System.out.println("   3. View Library Statistics");
            System.out.println("   4. Exit");
            int choice = inputProcessor.getIntInput("Please enter choice: ");
            switch (choice) {
                case 1:
                    viewTotalStudents();
                    break;
                case 2:
                    searchBooks();
                    break;
                case 3:
                    viewStatistics();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
            new DataStorage().saveLibrary(library);
        }
    }

    private void viewTotalStudents() {
        int totalStudents = library.getTotalStudents();
        System.out.println("Total Registered Students: " + totalStudents);
    }

    private void searchBooks() {
        String title = inputProcessor.getStringInput("Enter book title to search: ");
        List<Book> results = library.searchBooks(title, null, null);
        if (results.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("Search Results:");
            for (Book book : results) {
                System.out.println(book.getTitle() + " by " + book.getAuthor() +
                        " (" + book.getPublicationYear() + ") - " + book.getPageCount() + " pages");
            }
        }
    }

    private void viewStatistics() {
        System.out.println("Library Statistics:");
        System.out.println("Total Students: " + library.getTotalStudents());
        System.out.println("Total Books: " + library.getTotalBooks());
        System.out.println("Total Borrows: " + library.getTotalBorrows());
        System.out.println("Current Borrows: " + library.getCurrentBorrows());
    }
}