package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime at;

    public Event(String description, String at) {
        super(description);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd H:m");
        this.at = LocalDateTime.parse(at, format);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("d MMM yyyy, HHmm")) + ")";
    }

}