import java.util.Scanner;

public class ATMInterface {

    private static final int MAX_PIN_ATTEMPTS = 3;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        BankAccount account =
                new BankAccount("XXXX-1234", "1234", 10000);

        System.out.println("\n======================================");
        System.out.println("          SMART ATM SYSTEM");
        System.out.println("======================================");

        // PIN Authentication
        boolean authenticated = false;

        for (int attempt = 1; attempt <= MAX_PIN_ATTEMPTS; attempt++) {

            System.out.print("Enter your 4-digit PIN: ");
            String pin = scanner.next();

            if (account.authenticate(pin)) {
                authenticated = true;
                System.out.println("\nLogin successful!");
                break;
            }

            System.out.println(
                "Incorrect PIN. Attempts remaining: "
                + (MAX_PIN_ATTEMPTS - attempt)
            );
        }

        if (!authenticated) {
            System.out.println("\nAccount temporarily locked.");
            scanner.close();
            return;
        }

        int choice;

        do {

            displayMenu();

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    showBalance(account);
                    break;

                case 2:
                    depositMoney(account, scanner);
                    break;

                case 3:
                    withdrawMoney(account, scanner);
                    break;

                case 4:
                    account.showTransactions();
                    break;

                case 5:
                    System.out.println("\nThank you for using Smart ATM.");
                    System.out.println("Please collect your card.");
                    break;

                default:
                    System.out.println(
                        "Invalid option. Please choose 1-5."
                    );
            }

        } while (choice != 5);

        scanner.close();
    }

    private static void displayMenu() {

        System.out.println("\n======================================");
        System.out.println("              ATM MENU");
        System.out.println("======================================");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Transaction History");
        System.out.println("5. Exit");
        System.out.println("======================================");
    }

    private static void showBalance(BankAccount account) {

        System.out.printf(
            "\nAvailable Balance: ₹%.2f%n",
            account.getBalance()
        );
    }

    private static void depositMoney(
            BankAccount account,
            Scanner scanner) {

        System.out.print("Enter deposit amount: ₹");
        double amount = scanner.nextDouble();

        if (account.deposit(amount)) {

            System.out.printf(
                "Deposit successful: ₹%.2f%n",
                amount
            );

            showBalance(account);

        } else {
            System.out.println(
                "Invalid amount. Deposit must be greater than ₹0."
            );
        }
    }

    private static void withdrawMoney(
            BankAccount account,
            Scanner scanner) {

        System.out.print("Enter withdrawal amount: ₹");
        double amount = scanner.nextDouble();

        String result = account.withdraw(amount);

        switch (result) {

            case "SUCCESS":
                System.out.printf(
                    "Withdrawal successful: ₹%.2f%n",
                    amount
                );
                showBalance(account);
                break;

            case "INSUFFICIENT_BALANCE":
                System.out.println(
                    "Transaction failed: Insufficient balance."
                );
                break;

            case "DAILY_LIMIT":
                System.out.println(
                    "Transaction failed: Daily withdrawal limit exceeded."
                );
                break;

            default:
                System.out.println(
                    "Invalid withdrawal amount."
                );
        }
    }
}