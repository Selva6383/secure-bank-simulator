// Custom exception - thrown when a withdrawal is attempted with insufficient funds
public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
