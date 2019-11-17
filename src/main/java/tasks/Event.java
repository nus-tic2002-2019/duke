package tasks;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Event class is a child of Task class and
 * represent tasks with task description, due date and timeline
 */

public class Event extends Task{
    private LocalDate on;
    private LocalTime at;

    /**
     * constructor for Event class
     * @param description task description
     * @param on due date of task
     * @param at time the task is due
     */
    public Event(String description, LocalDate on, LocalTime at) {
        super(description);
        this.on = on;
        this.at = at;
    }

    @Override
    public String getDate(){
        return on.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String getTime() {
        return at.format(DateTimeFormatter.ofPattern("h:mma"));
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (on: " + getDate() + " at: " + getTime() + ")";
    }
}
