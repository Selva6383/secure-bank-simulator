import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Welcome to Secure Bank Simulator ===");

        while (running) {
            printMenu();
            int choice = readInt(sc);

            switch (choice) {
                case 1:
                    createAccount(sc, bank);
                    break;
                case 2:
                    deposit(sc, bank);
                    break;
                case 3:
                    withdraw(sc, bank);
                    break;
                case 4:
                    checkBalance(sc, bank);
                    break;
                case 5:
                    listAccounts(bank);
                    break;
                case 6:
                    running = false;
                    System.out.println("Thank you for using Secure Bank Simulator!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n1. Create Account\n2. Deposit\n3. Withdraw\n4. Check Balance\n5. List Accounts\n6. Exit");
        System.out.print("Choose an option: ");
    }

    private static void createAccount(Scanner sc, Bank bank) {
        System.out.print("Account number: ");
        String accNo = sc.next();
        System.out.print("Holder name: ");
        String name = sc.next();
        System.out.print("Opening balance: ");
        double balance = sc.nextDouble();
        System.out.print("Account type (1=Savings, 2=Current): ");
        int type = sc.nextInt();

        Account account = (type == 2)
                ? new CurrentAccount(accNo, name, balance)
                : new SavingsAccount(accNo, name, balance);

        bank.addAccount(account);
        System.out.println("Account created: " + account);
    }

    private static void deposit(Scanner sc, Bank bank) {
        Account acc = findAccount(sc, bank);
        if (acc == null) return;
        System.out.print("Deposit amount: ");
        double amount = sc.nextDouble();
        try {
            acc.deposit(amount);
            System.out.println("Deposit successful. New balance: " + acc.getBalance());
        } catch (InvalidTransactionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void withdraw(Scanner sc, Bank bank) {
        Account acc = findAccount(sc, bank);
        if (acc == null) return;
        System.out.print("Withdrawal amount: ");
        double amount = sc.nextDouble();
        try {
            acc.withdraw(amount);
            System.out.println("Withdrawal successful. New balance: " + acc.getBalance());
        } catch (InsufficientBalanceException | InvalidTransactionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void checkBalance(Scanner sc, Bank bank) {
        Account acc = findAccount(sc, bank);
        if (acc != null) {
            System.out.println(acc);
        }
    }

    private static void listAccounts(Bank bank) {
        List<Account> all = bank.getAllAccounts();
        if (all.isEmpty()) {
            System.out.println("No accounts yet.");
            return;
        }
        all.forEach(System.out::println);
    }

    private static Account findAccount(Scanner sc, Bank bank) {
        System.out.print("Account number: ");
        String accNo = sc.next();
        if (!bank.accountExists(accNo)) {
            System.out.println("Account not found.");
            return null;
        }
        return bank.getAccount(accNo);
    }

    private static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            sc.next();
        }
        return sc.nextInt();
    }
}
