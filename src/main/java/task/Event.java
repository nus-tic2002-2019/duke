package task;

import java.time.LocalDate;

public class Event extends Task {

    LocalDate date;

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
        return "[E][" + getStatusIcon() + "] " + this.description + " (at: " + this.date + ")";
    }
}
