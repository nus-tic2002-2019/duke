package duke.task;

import duke.exception.DukeException;
import duke.ui.Ui;
import duke.priority.Priority;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Event class is an extension class of Todo
 * It manages all event items stored by the user
 */
public class Event extends Todo {
    private String at;
    /**
    * Constructor method for Event class
    * @param description Describe the nature of the event
    * @param p priority of the deadline (Low, Medium, High)
    * @param at when is the event on
    */
    public Event(String description, Priority p, String at) {
        super(description,p);
        this.at = at;
    }
    /**
     * get when is the event on 
     * return datetime in a different format (Day Long Month Year)
     * @return When is the event on
     */
    public String getAt() {
        try {
            return Ui.printDateTime(this.at);
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
      return ("[E][" + this.getStatusIcon() + "] " + this.description + " priority: " + getp() + " (at: " + this.getAt() + ")");
    }
    /**
     * Override the method writeTask in the Task class
     * @return The text to be saved in the file
     */
    @Override
    public String writeTask(){
      return ("E | " + this.getStatus() + " | " + this.description  + " | " + getp() + " | " + this.at);
    }
    
    /**
     * Get the event date in datetime format
     * @return The date in datetime format
     */
    @Override
    public String getDateTime(){
        return this.at;
    }
}
