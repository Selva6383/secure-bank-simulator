// Custom exception - thrown when a deposit/withdrawal amount is invalid (e.g. negative or zero)
public class InvalidTransactionException extends Exception {
    public InvalidTransactionException(String message) {
        super(message);
    }
}
