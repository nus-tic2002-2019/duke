package tasks;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class is a child of Task class and
 * represent tasks with only task description and due date
 */

public class Deadline extends Task {
    private LocalDate by;

    /**
     * constructor for Deadline class
     * @param description task description
     * @param by due date of task
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getDate(){
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String getTime() {
        return "";
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
