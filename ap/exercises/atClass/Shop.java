package ap.exercises.atClass;

import java.util.ArrayList;

public class Shop {
    public static void main(String[] args) {
        ArrayList<Book> bookList = new ArrayList<>();
        ArrayList<Pen> penList = new ArrayList<>();

        bookList.add(new Book("Math", 100));
        bookList.add(new Book("Science", 200));
        penList.add(new Pen(200,"Black","bi"));
        penList.add(new Pen(200,"Blue","bi"));

    }
}

class Iteam {
    protected int price;
    public String toString(){
        return "0";
    }

}

class Book extends Iteam {
    private String title;
    private int bookPrice;

    public Book(String title, int bookPrice){
        this.title = title;
        this.bookPrice = bookPrice;
    }

    @Override
    public String toString() {
        return "title=" + title + ", bookPrice=" + bookPrice;
    }
}

class Pen extends Iteam {

    private String color,brand;
    public Pen(int price, String color, String brand) {
        this.price = price;
        this.color = color;
        this.brand = brand;
    }
    @Override
    public String toString() {
        return "Price : " + price + " Color : " + color + " Brand : " + brand;
    }
}
