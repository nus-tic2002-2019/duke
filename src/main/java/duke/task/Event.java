package duke.task;

import duke.others.DateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate date;

    public Event(String desc, LocalDate date) {
        super(desc, "E");
        this.date = date;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getFormattedDate() {
        return this.date.format(DateTimeFormatter.ofPattern(DateFormat.EVENT_AND_DEADLINE));
    }

    public String getStatusIconAndDesc() {
        return "[" + this.type + "][" + this.getStatusIcon() + "] " + this.desc + " (at: " + getFormattedDate() + ")";
    }
}