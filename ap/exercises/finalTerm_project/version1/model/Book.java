package ap.exercises.finalTerm_project.version1.model;

public class Book {
    private String bookId;
    private String title;
    private String author;
    private int publicationYear;
    private boolean isBorrowed;

    public Book(String bookId, String title, String author, int publicationYear) {
        setBookId(bookId);
        setTitle(title);
        setAuthor(author);
        setPublicationYear(publicationYear);
        this.isBorrowed = false;
    }

    public void setBookId(String bookId) {
        if (bookId == null || bookId.trim().isEmpty()) {
            throw new IllegalArgumentException("Book ID cannot be empty!");
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

    public void setBorrowed(boolean borrowed) {
        this.isBorrowed = borrowed;
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

    public boolean isBorrowed() {
        return isBorrowed;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author +
                ", Year: " + publicationYear + ", Status: " + (isBorrowed ? "Borrowed" : "Available");
    }
}