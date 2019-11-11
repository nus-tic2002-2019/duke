package exception;

/**
 * Signals an error caused by missing a task's description.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
