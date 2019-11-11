package Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import Ui.Ui;

public class Deadline extends Task {
    protected boolean isDeadline;
    protected String by;

    /***
     *
     * @param description for deadline task description
     * @param by string enter by user after keyword /by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        isDeadline = false;
    }

    /***
     *
     * @return Override the toString() method
     */
    @Override
    public String toString() {
        try {
            String byDate = by.trim();
            LocalDate d1 = LocalDate.parse(byDate);
            byDate = d1.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            return "[D]" + super.toString() + "(by:" + byDate + ")";
        } catch (DateTimeParseException e) {
            Ui.dateTimeInvalidFormat();
        }
        return "[D]" + super.toString() +  "(by:" + by  + ")" ;
    }
}
