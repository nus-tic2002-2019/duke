public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String saveTask() {
        int isDone;
        if(super.isDone)
            isDone = 1;
        else
            isDone = 0;
        return "D | " + isDone + " | " + super.saveTask() + " | " + by;
    }
}