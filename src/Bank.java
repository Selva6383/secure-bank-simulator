import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Demonstrates COLLECTIONS FRAMEWORK - HashMap for fast account lookup by account number,
// ArrayList to return an ordered list of all accounts when needed.
public class Bank {
    private Map<String, Account> accounts = new HashMap<>();

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public boolean accountExists(String accountNumber) {
        return accounts.containsKey(accountNumber);
    }

    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }
}
