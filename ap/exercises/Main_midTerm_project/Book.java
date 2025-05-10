package ap.exercises.Main_midTerm_project;

import java.time.Year;

public class Book {
    private String title;
    private String author;
//    private String publisher;
    private int publicationYear;
    private int pages;

    public Book(String title, String author, int publicationYear, int pages) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        if (!title.isEmpty()) {
            this.title = title;
        }
        else {
            throw new IllegalArgumentException("Title cannot be empty!");
        }
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        if (!author.isEmpty()) {
            this.author = author;
        }
        else {
            throw new IllegalArgumentException("Author cannot be empty!");
        }
    }

    public int getPublicationYear() {
        return publicationYear;
    }
    public void setPublicationYear(int publicationYear) {
        if (publicationYear >= 1950 && publicationYear <= Year.now().getValue()) {
            this.publicationYear = publicationYear;
        }
        else {
            throw new IllegalArgumentException("Publication Year cannot be greater than this year and less than 1950!");
        }
    }

    public int getPages() {
        return pages;
    }
    public void setPages(int pages) {
        if (pages >= 1) {
            this.pages = pages;
        }
        else {
            throw new IllegalArgumentException("Page count cannot be less than 1!");
        }
    }

    @Override
    public String toString() {
        return "Title : " + title + " | Author : " + author + " | Publication Year : " + publicationYear +
                " | Pages : " + pages;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Book book = (Book) obj;
        if (publicationYear != book.publicationYear) {
            return false;
        }
        return title.equals(book.title) && author.equals(book.author) && pages == book.pages;
    }
}
