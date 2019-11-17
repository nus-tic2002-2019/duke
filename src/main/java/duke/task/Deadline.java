package duke.task;

/**
 * The Deadline class extends the Task class to format the string output of Deadline tasks.
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return " [" + getStatusIcon() + "]" + "[D] " + getDescription() + " (by: " + by + ")";
    }
}
