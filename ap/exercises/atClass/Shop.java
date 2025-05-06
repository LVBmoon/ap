package ap.exercises.atClass;

import java.util.ArrayList;

/*this is the complete code I wanted to write but I got out of time I
changed the List because the two of the classes extends Iteam
and then use for to sout in the terminal (each sout cll the toString I wrote)
*/
public class Shop {
    public static void main(String[] args) {
        ArrayList<Iteam> itemList = new ArrayList<>();

        itemList.add(new Book("Math", 100));
        itemList.add(new Book("Science", 200));
        itemList.add(new Pen(200, "Black", "bi"));
        itemList.add(new Pen(200, "Blue", "bi"));

        for (Iteam item : itemList) {
            System.out.println(item);
        }
    }
}

class Iteam {
    protected int price;

    public String toString() {
        return "Item with price: " + price;
    }
}

class Book extends Iteam {
    private String title;

    public Book(String title, int price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book Title: " + title + ", Price: " + price;
    }
}

class Pen extends Iteam {
    private String color, brand;

    public Pen(int price, String color, String brand) {
        this.price = price;
        this.color = color;
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Pen - Price: " + price + ", Color: " + color + ", Brand: " + brand;
    }
}