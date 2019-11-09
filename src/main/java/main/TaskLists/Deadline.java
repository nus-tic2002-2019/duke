package main.TaskLists;

public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + super.toString() + " (by:" + by + ")";
    }
}

