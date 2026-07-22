# Secure Bank Simulator (Core Java)

Console-based banking application demonstrating core OOP principles.

## How to run
```
cd src
javac *.java
java Main
```

## Design
- **Account.java** — base class; fields are `private` (Encapsulation). Balance can only change through validated `deposit()`/`withdraw()` methods.
- **SavingsAccount.java / CurrentAccount.java** — extend `Account` (Inheritance). `CurrentAccount` overrides `withdraw()` to allow a controlled overdraft.
- **InsufficientBalanceException / InvalidTransactionException** — custom checked exceptions for domain-specific error handling.
- **Bank.java** — uses `HashMap<String, Account>` for O(1) account lookup by account number, and returns an `ArrayList` when listing all accounts (Collections Framework).
- **Main.java** — console menu tying everything together.

## Interview talking points
- **Encapsulation**: `balance` is private in `Account`; even subclasses can't touch it directly — they use a `protected debit()` hook, keeping the invariant "balance only changes through validated methods" intact everywhere.
- **Inheritance**: `SavingsAccount` and `CurrentAccount` reuse all of `Account`'s logic and only override what's different (`getAccountType()`, and `withdraw()` for overdraft).
- **Exception handling**: separated `InsufficientBalanceException` (business rule violation) from `InvalidTransactionException` (bad input) so callers can react differently if needed.
- **Collections**: chose `HashMap` for accounts because lookups by account number are the most frequent operation and need to be fast.
