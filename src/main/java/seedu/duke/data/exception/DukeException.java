package seedu.duke.data.exception;

import java.time.LocalDate;

public class DukeException extends Exception{

    /** 
     * @param message
     * @return 
     */
    public DukeException(String message){
        super(message);
    }
}