package seedu.duke.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    public LocalDateTime at;

    /** 
     * Constructs a new event with the description and date specified.
     * @param description
     * @param at
     */
    public Event(String description, LocalDateTime at){
        super(description);
        this.at = at;
    }

    /** 
     * Returns the Event in a String format.
     * @return String   The event in a string format.
     */
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + dateToString(at) + ")";
    }

    
    /** 
     * @return LocalDateTime
     */
    @Override
    public LocalDateTime getDateTime(){
        return this.at;
    }

    /** 
     * Converts the LocalDateTime object of a Event to a String object with the format (d/MM/yyyy HHmm);
     * @param dateTime  The date and time of the event in a specified date and time format.
     * @return String   The date and time for the event.
     */
    public String dateToString(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        return dateTime.format(formatter);
    }
}