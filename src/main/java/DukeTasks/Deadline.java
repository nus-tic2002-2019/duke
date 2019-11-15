package DukeTasks; 

// Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + this.getStatusIcon() +" "+ super.toString() + " (by: " + by + ")";
    }
}