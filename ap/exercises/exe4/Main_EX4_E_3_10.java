package ap.exercises.exe4;

public class Main_EX4_E_3_10 {
    public static void main(String[] args) {
        CashRegister register = new CashRegister();

        register.addItem(12.99);
        register.addItem(5.49);
        register.addItem(32.55);

        register.printReceipt();
    }
}
/*

••• E3.10 Add a method printReceipt to the CashRegister class. The method should print the
prices of all purchased items and the total amount due. Hint: You will need to form
a string of all prices. Use the concat method of the String class to add additional items
to that string. To turn a price into a string, use the call String.valueOf(price).*/