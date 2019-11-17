package duke.task;

/**
 * The Event class extends the Task class to format the string output of Event tasks.
 */
public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return " [" + getStatusIcon() + "]" + "[E] " + getDescription() + " (on: " + at + ")";
    }
}
