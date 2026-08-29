import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private final String type;
    private final double amount;
    private final double balance;
    private final LocalDateTime dateTime;

    public Transaction(String type, double amount, double balance) {
        this.type = type;
        this.amount = amount;
        this.balance = balance;
        this.dateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return String.format(
            "%s | Amount: ₹%.2f | Balance: ₹%.2f | %s",
            type,
            amount,
            balance,
            dateTime.format(formatter)
        );
    }
}