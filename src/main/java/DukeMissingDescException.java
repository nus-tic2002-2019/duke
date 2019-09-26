package main.java;

public class DukeMissingDescException extends DukeException {
    private String message;
    public DukeMissingDescException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
