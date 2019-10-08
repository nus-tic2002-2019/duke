package seedu.duke.data.exception;

/**
 * The DukeEmptyException 
 */
public class DukeEmptyException extends Exception{
    public DukeEmptyException(String taskName){
        super("The description of a " + taskName + " cannot be empty.");
    }
}