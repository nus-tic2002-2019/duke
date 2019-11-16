package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * The child class of Task.
 * */
public class Deadline extends Task {
    protected String date;
    protected Date by;
    /**
     * Creation of new Deadline with description.
     * @param description description of Deadline.
     * @param date date information for saving date.
     * @param by date information for printing date.
     * */
    public Deadline(String description, String date, Date by) {
        super(description);
        this.date = date;
        this.by = by;
    }
    /**
     * Update the printing format by adding icon and date for deadline.
     * @return printing format.
     * */
    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat ("MMM d yyyy");
        String dateString = format.format(by);
        return "[D]" + super.toString() + " (by: " + dateString + ")";
    }
    /**
     * Update the saving format by adding icon and date for deadline.
     * @return printing format.
     * */
    @Override
    public String save_toString() {
        return "D | " + super.save_toString() + "| " + date;
    }
}
