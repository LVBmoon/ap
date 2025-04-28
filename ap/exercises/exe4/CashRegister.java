package ap.exercises.exe4;

import java.util.ArrayList;

public class CashRegister {
    private ArrayList<Double> items;

    public CashRegister() {
        items = new ArrayList<>();
    }

    public void addItem(double price) {
        items.add(price);
    }

    public double getTotal() {
        double total = 0;
        for (double price : items) {
            total += price;
        }
        return total;
    }

    public void printReceipt() {
        String receipt = "Items:\n";
        for (double price : items) {
            receipt = receipt.concat(String.valueOf(price)).concat("\n");
        }
        receipt = receipt.concat("Total: ").concat(String.valueOf(getTotal()));
        System.out.println(receipt);
    }
}
