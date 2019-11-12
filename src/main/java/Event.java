public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
    public Event(String status, String description, String at) {
        super(description);
        this.at = at;
        this.setStatus(status);
    }

    @Override
    public char getTaskType() {
        return 'E';
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }

    public String getLast(){
        this.at = at;
        return at;
    }
}