package ap.exercises.finalTerm_project.version2.model;

public class Book {
    private String bookId;
    private String title;
    private String author;
    private int publicationYear;
    private int pageCount;
    private boolean isAvailable;
    private int borrowCount;

    public Book(String bookId, String title, String author, int publicationYear, int pageCount) {
        setBookId(bookId);
        setTitle(title);
        setAuthor(author);
        setPublicationYear(publicationYear);
        setPageCount(pageCount);
        this.isAvailable = true;
        this.borrowCount = 0;
    }

    public void setBookId(String bookId) {
        if (bookId == null || bookId.trim().isEmpty()) {
            throw new IllegalArgumentException("Book ID cannot be empty! fill it out :)");
        }
        this.bookId = bookId.trim();
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty!");
        }
        this.title = title.trim();
    }

    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be empty!");
        }
        this.author = author.trim();
    }

    public void setPublicationYear(int publicationYear) {
        if (publicationYear < 0) {
            throw new IllegalArgumentException("Publication year cannot be negative!");
        }
        this.publicationYear = publicationYear;
    }

    public void setPageCount(int pageCount) {
        if (pageCount <= 1) {
            throw new IllegalArgumentException("Page count must be more than one!");
        }
        this.pageCount = pageCount;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public int getPageCount() {
        return pageCount;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getBorrowCount() {
        return borrowCount;
    }

    public void incrementBorrowCount() {
        borrowCount++;
    }

    @Override
    public String toString() {
        return title + " (ID: " + bookId + ") by " + author + " (" + publicationYear + ") - " +
                pageCount + " pages - " + (isAvailable ? "Available" : "Borrowed");
    }

}