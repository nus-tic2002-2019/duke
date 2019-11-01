package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    protected String date;
    protected Date by;

    public Deadline(String description, String date, Date by) {
        super(description);
        this.date = date;
        this.by = by;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat ("MMM d yyyy");
        String dateString = format.format(by);
        return "[D]" + super.toString() + " (by: " + dateString + ")";
    }

    @Override
    public String save_toString() {
        return "[D]" + super.toString() + "(by: " + date + ")";
    }
}
