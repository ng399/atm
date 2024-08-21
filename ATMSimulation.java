import java.util.ArrayList;
import java.util.Scanner;

public class ATMSimulation {
    private static double balance = 5000.00; 
    private static int pin = 9876; 
    private static ArrayList<String> transactionHistory = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean authenticated = false;

        System.out.println("Enter your PIN: ");
        int inputPin = scanner.nextInt();
        if (inputPin == pin) {
            authenticated = true;
        } else {
            System.out.println("Incorrect PIN! Exiting...");
            System.exit(0);
        }

        do {
            System.out.println("Welcome to the ATM Machine!");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Change PIN");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    depositMoney(scanner);
                    break;
                case 3:
                    withdrawMoney(scanner);
                    break;
                case 4:
                    changePin(scanner);
                    break;
                case 5:
                    showTransactionHistory();
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM Machine. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }

            System.out.println();
        } while (choice != 6);

        scanner.close();
    }

    private static void checkBalance() {
        System.out.printf("Your current balance is: $%.2f%n", balance);
        transactionHistory.add("Checked balance: $" + balance);
    }

    private static void depositMoney(Scanner scanner) {
        System.out.print("Enter the amount to deposit: $");
        double depositAmount = scanner.nextDouble();
        if (depositAmount > 0) {
            balance += depositAmount;
            System.out.printf("Successfully deposited $%.2f%n", depositAmount);
            transactionHistory.add("Deposited: $" + depositAmount);
            checkBalance();
        } else {
            System.out.println("Invalid amount! Please enter a positive amount.");
        }
    }

    private static void withdrawMoney(Scanner scanner) {
        System.out.print("Enter the amount to withdraw: $");
        double withdrawAmount = scanner.nextDouble();
        if (withdrawAmount > 0 && withdrawAmount <= balance) {
            balance -= withdrawAmount;
            System.out.printf("Successfully withdrew $%.2f%n", withdrawAmount);
            transactionHistory.add("Withdrew: $" + withdrawAmount);
            checkBalance();
        } else if (withdrawAmount > balance) {
            System.out.println("Insufficient balance! Please enter a valid amount.");
        } else {
            System.out.println("Invalid amount! Please enter a positive amount.");
        }
    }

    private static void changePin(Scanner scanner) {
        System.out.print("Enter your current PIN: ");
        int currentPin = scanner.nextInt();
        if (currentPin == pin) {
            System.out.print("Enter your new PIN: ");
            int newPin = scanner.nextInt();
            System.out.print("Confirm your new PIN: ");
            int confirmNewPin = scanner.nextInt();
            if (newPin == confirmNewPin) {
                pin = newPin;
                System.out.println("PIN successfully changed.");
                transactionHistory.add("Changed PIN");
            } else {
                System.out.println("PINs do not match! Please try again.");
            }
        } else {
            System.out.println("Incorrect current PIN! Please try again.");
        }
    }

    private static void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        }
         else {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }
}