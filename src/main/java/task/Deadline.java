package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd H:m");
        this.by = LocalDateTime.parse(by, format);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("d MMM yyyy, HHmm")) + ")";
    }

}