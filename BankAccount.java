import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private final String accountNumber;
    private final String pin;

    private double balance;
    private double dailyWithdrawn;
    private static final double DAILY_LIMIT = 20000;

    private final List<Transaction> transactions = new ArrayList<>();

    public BankAccount(String accountNumber, String pin, double initialBalance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = initialBalance;
    }

    public boolean authenticate(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {

        if (amount <= 0) {
            return false;
        }

        balance += amount;

        transactions.add(
            new Transaction("DEPOSIT", amount, balance)
        );

        return true;
    }

    public String withdraw(double amount) {

        if (amount <= 0) {
            return "INVALID";
        }

        if (amount > balance) {
            return "INSUFFICIENT_BALANCE";
        }

        if (dailyWithdrawn + amount > DAILY_LIMIT) {
            return "DAILY_LIMIT";
        }

        balance -= amount;
        dailyWithdrawn += amount;

        transactions.add(
            new Transaction("WITHDRAW", amount, balance)
        );

        return "SUCCESS";
    }

    public void showTransactions() {

        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
            return;
        }

        System.out.println("\n--------- TRANSACTION HISTORY ---------");

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }

        System.out.println("---------------------------------------");
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}