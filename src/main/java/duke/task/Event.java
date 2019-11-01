package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    protected String date;
    protected Date at;

    public Event (String description, String date, Date at) {
        super(description);
        this.date = date;
        this.at = at;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat ("MMM d yyyy");
        String dateString = format.format(at);
        return "[E]" + super.toString() + " (at: " + dateString + ")";
    }

    @Override
    public String save_toString() {
        return "[E]" + super.toString() + "(at: " + date + ")";
    }
}