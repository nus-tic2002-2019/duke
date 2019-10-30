package ERROR_HANDLING;

public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        super();
    }
    public InvalidCommandException(String msg) {
        super(msg);
    }
}
