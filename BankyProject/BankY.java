import java.io.*;
import java.util.*;

public class BankY {
    private static final String FILE_NAME = "accounts.dat";
    private static Map<String, BankAccount> accounts = new HashMap<>();

    public static void main(String[] args) {
        loadAccounts();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- BankY Menu ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer Funds");
            System.out.println("5. Check Balance");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // consume the newline

            switch (choice) {
                case 1 -> createAccount(scanner);
                case 2 -> deposit(scanner);
                case 3 -> withdraw(scanner);
                case 4 -> transferFunds(scanner);
                case 5 -> checkBalance(scanner);
                case 6 -> saveAccounts();
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }

    private static void createAccount(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter account holder name: ");
        String accountHolderName = scanner.nextLine();
        System.out.print("Enter initial deposit: ");
        double initialDeposit = scanner.nextDouble();

        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account with this number already exists.");
        } else {
            BankAccount account = new BankAccount(accountNumber, accountHolderName, initialDeposit);
            accounts.put(accountNumber, account);
            System.out.println("Account created successfully.");
        }
    }

    private static void deposit(Scanner scanner) {
        BankAccount account = getAccount(scanner);
        if (account != null) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
        }
    }

    private static void withdraw(Scanner scanner) {
        BankAccount account = getAccount(scanner);
        if (account != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
        }
    }

    private static void transferFunds(Scanner scanner) {
        System.out.print("Enter sender account number: ");
        String senderAccountNumber = scanner.nextLine();
        BankAccount sender = accounts.get(senderAccountNumber);

        if (sender != null) {
            System.out.print("Enter receiver account number: ");
            String receiverAccountNumber = scanner.nextLine();
            BankAccount receiver = accounts.get(receiverAccountNumber);

            if (receiver != null) {
                System.out.print("Enter transfer amount: ");
                double amount = scanner.nextDouble();
                sender.transfer(receiver, amount);
            } else {
                System.out.println("Receiver account not found.");
            }
        } else {
            System.out.println("Sender account not found.");
        }
    }

    private static void checkBalance(Scanner scanner) {
        BankAccount account = getAccount(scanner);
        if (account != null) {
            System.out.println(account);
        }
    }

    private static BankAccount getAccount(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = accounts.get(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
        }

        return account;
    }

    private static void saveAccounts() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(accounts);
            System.out.println("Accounts saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    private static void loadAccounts() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            accounts = (HashMap<String, BankAccount>) in.readObject();
            System.out.println("Accounts loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No existing accounts found, starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
    }
}
