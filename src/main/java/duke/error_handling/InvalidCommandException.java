package duke.error_handling;

/**
 * This Exception triggered when no matched Keyword in the duke.command list
 */

public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        super();
    }
    public InvalidCommandException(String msg) {
        super(msg);
    }
}
