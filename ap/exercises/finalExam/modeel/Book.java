package ap.exercises.finalExam.modeel;

public class Book extends Products {
    private String name;
    private String bookName;
    private String authorName;
    private int price;

    public Book(String name,String bookName,String authorName, int price) {
        super(name,price);
        this.name = name;
        this.price = price;
        this.bookName = bookName;
        this.authorName = authorName;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Name: " + name + " BookName: " + bookName + " Author: " + authorName + " Price: " + price ;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookName.equals(book.bookName) && authorName.equals(book.authorName);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, bookName, authorName, price);
    }
}
