package duke.exception;
/**
 * The DukeException class extends from th Exception class and provides a way to give meaningful errors for the users
 */
public class DukeException extends Exception {
    /**
     * The DukeException constructor accept messages which you wish to display to the users
     * @param message meaningful message regarding the error
     * @see Exception
     */
    public DukeException(String message){
        super(message);
    }

}

