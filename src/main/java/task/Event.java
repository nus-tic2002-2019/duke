package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public Event (String content) {
        super(content);
    }
    private LocalDateTime at;
    private LocalDateTime till;

    public Event(String content, LocalDateTime from, LocalDateTime to){
        super(content);
        this.at = from;
        this.till = to;
    }

    public LocalDateTime getStartTime() {
        return at;
    }
    public LocalDateTime getEndTime() {
        return till;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd HH:mm"))
                + "-" + till.format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
    }
}

