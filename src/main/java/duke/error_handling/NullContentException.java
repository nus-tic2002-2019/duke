package duke.error_handling;

public class NullContentException extends DukeException{
    public NullContentException() {
        super();
    }
    public NullContentException(String msg) {
        super(msg);
    }
}
