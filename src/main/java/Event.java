public class Event extends Task {

    protected String dayTime;

    public Event(String description, String dayTime) {
        super(description);
        this.dayTime = dayTime;

    }

    @Override
    public String toString(){
        return("[E]" + super.toString() + " (at: " + dayTime + ")");
    }
}
