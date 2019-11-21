package duke.task;

import duke.exception.DukeException;
import duke.ui.Ui;
import duke.priority.Priority;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Deadline class is an extension class of Todo
 * It manages all deadlines items stored by the user
 */
public class Deadline extends Todo {
    private String by;
    /**
    * Constructor method for Deadline class
    * @param description Describe the nature of the deadline
    * @param p priority of the deadline (Low, Medium, High)
    * @param by when is the deadline due
    */
    public Deadline(String description, Priority p, String by) {
        super(description,p);
        this.by = by;
    }
    /**
     * get when is the submission due 
     * return datetime in a different format (Day Long Month Year)
     * @return When is the submission due
     */
    public String getBy() {
        try {
            return Ui.printDateTime(this.by);
        } catch (DukeException ex) {
            Ui.response("☹ OOPS!!! Please include a valid date description'. (yyyy-mm-dd)");
        } catch (ParseException e){
            Ui.response("☹ OOPS!!! Please include a date description.");
        }
        return null;
    }
    
    /**
     * Overrides the method printTask in the Task class
     * @return the string to be printed
     */
    @Override
    public String printTask(){
        return ("[D][" + this.getStatusIcon() + "] " + this.description + " priority: " + getp() + " (by: " + this.getBy() + ")");
    }
    /**
     * Override the method writeTask in the Task class
     * @return The text to be saved in the file
     */
    @Override
    public String writeTask(){
        return ("D | " + this.getStatus() + " | " + this.description + " | " + getp() + " | " + this.by);
    }
    /**
     * Get the deadline date in datetime format
     * @return The date in datetime format
     */
    @Override
    public String getDateTime(){
        return this.by;
    }
}
