// Demonstrates INHERITANCE - reuses all fields/methods from Account,
// only adds savings-specific behavior (interest rate).
public class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.04; // 4% per annum

    public SavingsAccount(String accountNumber, String holderName, double openingBalance) {
        super(accountNumber, holderName, openingBalance);
    }

    public void applyInterest() throws InvalidTransactionException {
        double interest = getBalance() * INTEREST_RATE;
        deposit(interest);
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }
}
