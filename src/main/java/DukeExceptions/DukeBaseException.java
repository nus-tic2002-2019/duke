package DukeExceptions;

public class DukeBaseException extends Exception {

    private String input;

    public DukeBaseException(String message){

        this.input = message;
    }

    public String getMessage() {

        return input;
    }
}
