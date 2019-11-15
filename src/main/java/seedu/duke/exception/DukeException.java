package seedu.duke.exception;

public class DukeException extends Exception{

    /** 
     * Constructs an DukeEmptyException with the specified detailed message.
     * @param message  The detail message which was thrown by the error.
     */
    public DukeException(String message){
        super(message);
    }
}