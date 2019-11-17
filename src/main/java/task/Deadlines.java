//level 7.more oop
/**
 *  Deadline command for storing Data "description" and "due Date & Time"
 */

package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends task.Task {
    //private boolean isDone;
    //private String by;
    private LocalDateTime by;
    //private String description;

    public Deadlines(String description, LocalDateTime by) {
        super(description);
        //isDone = false;
        this.by = by;
    }

    public LocalDateTime getDate() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}
