
import java.util.Date;

public class Deadline extends Task {

    public Date by;

    /**
     * Constructs a new Deadline and initialise with the specified input and time by the user .
     * @param description user input
     * @param by date time
     */
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