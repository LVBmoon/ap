package ap.exercises.exe4;

public class BankAccountTester {
    public static void main(String[] args) {
        //E_3_7
        BankAccount bankAccount1 = new BankAccount();

        bankAccount1.deposit(1000);
        bankAccount1.withdraw(500);
        bankAccount1.withdraw(400);

        double remainingBalance = bankAccount1.getBalance();
        System.out.println("The expected remain: $100.0");
        System.out.println("The remain money: $" + remainingBalance);
        System.out.println("---------------------------------");

        //E_3_8
        BankAccount momsSavings = new BankAccount(1000);

        momsSavings.addInterest(10);
        double momsSavingsRemainingBalance = momsSavings.getBalance();
        System.out.println("The expected remain: $1100.0");
        System.out.println("The remaining balance: $" + momsSavingsRemainingBalance);
    }
}
