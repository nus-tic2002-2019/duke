package main.duke.exception;

public class DukeMissingDescException extends DukeException {
    private String message;
    public DukeMissingDescException(String classStr) {
        this.message = String.format("OOPS!!! The description of %s cannot be empty.", classStr);
    }
    @Override
    public String getMessage() {
        return message;
    }
}
