// Demonstrates INHERITANCE - a second account type extending the same base class,
// showing how different accounts share common logic but override behavior where needed.
public class CurrentAccount extends Account {
    private static final double OVERDRAFT_LIMIT = 5000.0;

    public CurrentAccount(String accountNumber, String holderName, double openingBalance) {
        super(accountNumber, holderName, openingBalance);
    }

    // Overridden withdraw() - allows going negative up to the overdraft limit
    @Override
    public void withdraw(double amount) throws InsufficientBalanceException, InvalidTransactionException {
        if (amount <= 0) {
            throw new InvalidTransactionException("Withdrawal amount must be greater than zero.");
        }
        if (getBalance() - amount < -OVERDRAFT_LIMIT) {
            throw new InsufficientBalanceException(
                "Overdraft limit exceeded. Max overdraft allowed: " + OVERDRAFT_LIMIT);
        }
        debit(amount); // uses protected hook from Account to safely reduce balance below zero
    }

    @Override
    public String getAccountType() {
        return "Current";
    }
}
