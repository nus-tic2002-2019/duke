import java.util.Date;
public class Event extends Task {
    protected Date at;

    public Event(String description, Date at) {
        super(description);
        Date now = new Date();
        assert at.after(now):"Event date cannot be earlier than now!";
        this.at = at;
    }

    public void markAsDone() {
        isDone = true;
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}