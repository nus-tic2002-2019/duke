package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate date;

    /**
     * Constructor of Event
     * @param description
     * @param value
     * @param date
     */
    public Event(String description, int value, LocalDate date){
        super(description, value);
        this.date = date;
    }

    /**
     * Constructor of Event
     * @param description
     * @param date
     */
    public Event(String description, LocalDate date){
        super(description, 0);
        this.date = date;
    }



    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String date = this.date.format(formatter);
        return "[E][" + getStatusIcon() + "] " + this.description + " (at: " + date + ")";
    }
}
