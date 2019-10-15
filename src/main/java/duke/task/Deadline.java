package duke.task;

import duke.task.Task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String input, String by) {
        super(input);
        by = by.trim();
        this.by = by;
    }

    //Accessor
    @Override
    public String printTask() {
        return "[D]" + super.printTask() + " (by: " + by + ")";
    }
}
