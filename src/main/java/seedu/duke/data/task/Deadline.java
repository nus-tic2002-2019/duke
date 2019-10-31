package seedu.duke.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    public LocalDateTime by;

    /** 
     * Constructs a new Deadline with the description and date specified.
     * @param description
     * @param by
     */
    public Deadline(String description, LocalDateTime by){
        super(description);
        this.by = by;
    }

    /** 
     * Returns the Deadline in a String format.
     * @return String   The deadline in a String format.
     */
    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + dateToString(by) + ")";
    }

    /** 
     * @return LocalDateTime
     */
    @Override
    public LocalDateTime getDateTime(){
        return this.by;
    }

    /** 
     * Converts the LocalDateTime object of a Deadline to a String object with the format (d/MM/yyyy HHmm);
     * @param dateTime  The date and time of the deadline in a specified date and time format.
     * @return String   The date and time for the deadline.
     */
    public String dateToString(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        return dateTime.format(formatter);
    }
}