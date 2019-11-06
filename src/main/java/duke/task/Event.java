package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * The child class of Task.
 * */
public class Event extends Task {
    protected String date;
    protected Date at;
    /**
     * Creation of new Deadline with description.
     * @param description of Event.
     * @param date for saving date.
     * @param at for printing date.
     * */
    public Event (String description, String date, Date at) {
        super(description);
        this.date = date;
        this.at = at;
    }
    /**
     * Update the printing format by adding icon and date for event.
     * @return printing format.
     * */
    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat ("MMM d yyyy");
        String dateString = format.format(at);
        return "[E]" + super.toString() + " (at: " + dateString + ")";
    }
    /**
     * Update the saving format by adding icon and date for event.
     * @return printing format.
     * */
    @Override
    public String save_toString() {
        return "E | " + super.save_toString() + "| " + date;
    }
}