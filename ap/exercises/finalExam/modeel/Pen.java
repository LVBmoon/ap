package ap.exercises.finalExam.modeel;

public class Pen extends Products {
    private String name;
    private int price;
    private Color color;
    public enum Color { BLACK, BLUE, RED, GREEN }

    public Pen(String name, int price, Color color) {
        super(name, price);
        this.price = price;
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Name : " + name + "  Price : " + price + "  Color : " + color;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) return false;
        Pen pen = (Pen) o;
        return color == pen.color;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, price, color);
    }
}
