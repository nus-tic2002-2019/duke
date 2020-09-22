package exception;

public class EmptyException extends Exception {
    public EmptyException(String type) {
        super("☹ OOPS!!! The description of " + type + " cannot be empty.");
    }
}
