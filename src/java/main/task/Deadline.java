package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected boolean isDone;
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        isDone = false;
        this.by = by;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public LocalDate getDeadLine() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D] [" + super.getStatusIcon() + "]" + super.getDescription() + "(by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String save() {
        return "D" + " | " + (this.isDone() ? 1 : 0) + " | " + this.getDescription().trim()
                + " | " + this.getDeadLine().toString().trim() + System.lineSeparator();
    }
}