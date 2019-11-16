package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public Deadline (String content) {
        super(content);
    }
    private LocalDate by;
    public Deadline (String content, LocalDate by){
        super(content);
        this.by = by;
    }
    public LocalDate getDate() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd")) + ")";
    }
}
