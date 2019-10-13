package tasklist;

import tasklist.Task;

public class Deadlines extends Task {
    protected String by;

    public Deadlines(String description, String by){

        super(description);
        this.by = by;
    }

    public Deadlines(String description, String by, boolean isDone){

        super(description);
        this.by = by;
        super.isDone = isDone;
    }

    @Override
    public String getDescription() {
        return "[D]" + "[" + getStatusIcon() + "]" + super.getDescription() + " (by: " + by + ")";
    }

}
