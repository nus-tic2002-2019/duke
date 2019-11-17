package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    // Date time variable
    protected LocalDateTime at;

    public Event(String description, LocalDateTime at){
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
        return "[E]" + super.toString() + " (at: " + at.format(formatter) + ")";
    }

    @Override
    public String saveTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        int isDone;
        if (super.isDone)
            isDone = 1;
        else
            isDone = 0;
        return "E | " + isDone + " | " + super.saveTask() + " | " + at.format(formatter);
    }
}
