package seedu.duke.exception;

/**
 * Signals an DukeEmptyException when the description of the task is empty.
 */

public class DukeEmptyException extends Exception{
    
    /** 
     * Constructs an DukeEmptyException with the specified task name with the empty description message.
     * @param taskName  The task name which has a empty description.
     */
    public DukeEmptyException(String taskName){
        super("The description of a " + taskName + " cannot be empty.");
    }
}