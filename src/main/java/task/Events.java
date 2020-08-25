//level 7.more oop
/**
 *  Event command for storing Data "description" and "Date & Time"
 */

package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends task.Task {
    //private boolean isDone;
        //private String at;
    private LocalDateTime at;
   // private String description;

    public Events(String description, LocalDateTime at) {
        super(description);
        //isDone = false;
        this.at = at;
    }

    public LocalDateTime getDate() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}


