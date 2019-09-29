package seedu.duke.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateToString(at) + ")";
    }

    public String dateToString(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        return dateTime.format(formatter);
    }
}