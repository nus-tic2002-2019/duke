package tasklist;

import tasklist.Task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, String at, boolean isDone) {
        super(description);
        this.at = at;
        super.isDone = isDone;
    }

    @Override
    public String getDescription() {
        return "[E]" + "[" + getStatusIcon() + "]" + super.getDescription() + " (by: " + at + ")";
    }
}
