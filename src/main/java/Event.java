public class Event extends Task {
    protected String by;

    public Event (String description, String by) {
        super(description) ;
        this.by = by;
        //this.timing = timing;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + by + ")";
    }
}
