package duke;

public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
        System.out.println(message);
    }
}
