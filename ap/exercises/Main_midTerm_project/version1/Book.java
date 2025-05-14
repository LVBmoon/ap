package ap.exercises.Main_midTerm_project.version1;

import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private String author;
    private int publicationYear;
    private int pageCount;
    private boolean isAvailable;

    public Book(String title, String author, int publicationYear, int pageCount) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.pageCount = pageCount;
        this.isAvailable = true;
    }

    // Getters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublicationYear() { return publicationYear; }
    public int getPageCount() { return pageCount; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public String toString() {
        return title + " by " + author + " (" + publicationYear + ") - " + pageCount + " pages";
    }
}
