package duke.task;

import duke.others.DateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate date;

    public Deadline(String desc, LocalDate date) {
        super(desc, "D");
        this.date = date;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getFormattedDate() {
        return this.date.format(DateTimeFormatter.ofPattern(DateFormat.EVENT_AND_DEADLINE));
    }

    public String getStatusIconAndDesc() {
        return "[" + this.type + "][" + this.getStatusIcon() + "] " + this.desc + " (by: " + getFormattedDate() + ")";
    }
}