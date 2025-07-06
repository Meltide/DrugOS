package sys.except;

public class UnknownCommandException extends RuntimeException {
    public UnknownCommandException(String message) {
        super(message);
    }
}
