package ap.exercises.exe4;

public class BankAccount {
    private double balance;

    public BankAccount(){
        balance = 0;

    }

    public void deposit(double amount) {
        if(amount > 0){
            balance += amount;
        }
        else{
            throw new IllegalArgumentException("You can't deposit less than 0!");
        }
    }

    public void withdraw(double amount) {
        if(amount > 0 && balance >= amount){
            balance -= amount;
        }
        else{
            throw new IllegalArgumentException("You can't withdraw less than 0 and more than what you have!!");
        }
    }
    public double getBalance() {
        return balance;
    }
}
