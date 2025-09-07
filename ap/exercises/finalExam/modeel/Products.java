package ap.exercises.finalExam.modeel;

public abstract class Products {
    private String name;
    private int price;

    public Products(String name, int price) {
        this.name = name;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Name : " + name + "Price : " + price ;
    }

    public int compareTo(Products o) {
        return Integer.compare(this.price, o.price);
    }
}
