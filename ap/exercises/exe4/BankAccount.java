package ap.exercises.exe4;

public class BankAccount {
    private double balance;

    public BankAccount(){//E_3_7
        balance = 0;

    }

    public BankAccount(double balance){//E_3_8
        this.balance = balance;

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

    public void addInterest(double rate){
        if (rate > 0) {
            balance += (balance * rate / 100);
        }
        else {
            throw new IllegalArgumentException("Interest rate must be positive!");
        }
    }
}
