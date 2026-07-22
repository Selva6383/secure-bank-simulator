// Base class - demonstrates ENCAPSULATION
// Fields are private so no other class can directly change balance;
// all access happens through controlled public methods.
public class Account {
    private String accountNumber;
    private String holderName;
    private double balance;

    public Account(String accountNumber, String holderName, double openingBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = openingBalance;
    }

    // Controlled read access
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    // Controlled write access - validated, not a direct field set
    public void deposit(double amount) throws InvalidTransactionException {
        if (amount <= 0) {
            throw new InvalidTransactionException("Deposit amount must be greater than zero.");
        }
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientBalanceException, InvalidTransactionException {
        if (amount <= 0) {
            throw new InvalidTransactionException("Withdrawal amount must be greater than zero.");
        }
        if (amount > balance) {
            throw new InsufficientBalanceException(
                "Insufficient balance. Available balance: " + balance);
        }
        balance -= amount;
    }

    // Protected hook so subclasses (e.g. CurrentAccount) can apply custom withdrawal
    // rules like overdraft, without exposing the private balance field directly.
    protected void debit(double amount) {
        balance -= amount;
    }

    // Overridden by subclasses to show account-type-specific info (used with Inheritance)
    public String getAccountType() {
        return "General";
    }

    @Override
    public String toString() {
        return "[" + getAccountType() + "] " + accountNumber + " | " + holderName
                + " | Balance: " + String.format("%.2f", balance);
    }
}
