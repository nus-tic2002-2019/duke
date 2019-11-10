package tasklist;

public class Event extends Task {

    protected String dayTime ="Day and Time not specified";
    public String Type = "Event";

    public Event(String description, String dayTime) {
        super(description);
        this.dayTime = dayTime;
    }

    public Event(String description, Boolean status) {
        super(description,status);
    }

    public Event(String description,Boolean status, String dayTime) {
        super(description,status);
        this.dayTime = dayTime;

    }
    @Override
    public String toString() {
        return("[E]" + super.toString() + " (at: " + this.dayTime + ")");
    }

    @Override
    public String saveFormat() {
        return ("E " + super.saveFormat() + " | " + this.dayTime);
    }
}
