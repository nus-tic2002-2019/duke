import java.util.Date;

public class Deadline extends Task {

    protected Date by;

    public Deadline(String description, Date by) {
        super(description);
        Date now = new Date();
        assert by.after(now):"Deadline cannot be earlier than now!";
        this.by = by;
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}