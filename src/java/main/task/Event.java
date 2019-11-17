/*
Event.java
define Event task.
*/

package task;

import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected boolean isDone;
    protected LocalDate at;

    /*
    constructor of event object.
    @param description event task description
    @param time deadline of event object.
    */

    public Event(String description, LocalDate at) {
        super(description);
        isDone = false;
        this.at = at;
    }

    public void setDone(boolean done) {
        isDone = done;
    }


    public LocalDate getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E] [" + super.getStatusIcon() + "]" + super.getDescription() + "(at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String save() {
        return "E" + " | " + (this.isDone() ? 1 : 0) + " | " + this.getDescription().trim()
                + " | " + this.getAt().toString().trim() + System.lineSeparator();
    }
}
