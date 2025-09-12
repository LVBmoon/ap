package ap.exercises.finalTerm_project.version2.service;

import ap.exercises.finalTerm_project.version2.core.Library;
import ap.exercises.finalTerm_project.version2.data.InputProcessor;
import ap.exercises.finalTerm_project.version2.model.Book;
//---
import java.util.List;

public class GuestService extends BaseService {

    public GuestService(Library library, InputProcessor inputProcessor) {
        super(library, inputProcessor);
    }

    public void viewTotalStudents() {
        int totalStudents = library.getTotalStudents();
        System.out.println("Total Registered Students: " + totalStudents);
    }

    public void searchBooks() {
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

    public void viewStatistics() {
        System.out.println("Library Statistics:");
        System.out.println("Total Students: " + library.getTotalStudents());
        System.out.println("Total Books: " + library.getTotalBooks());
        System.out.println("Total Borrows: " + library.getTotalBorrows());
        System.out.println("Current Borrows: " + library.getCurrentBorrows());
    }
}