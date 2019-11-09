package main.TaskLists;

public class Event extends Task {

    private String At;

    public Event(String description, String At) {
        super(description);
        this.At = At;
    }

    @Override
    public String toString() {
        return "[E]"+ "[" +this.getStatusIcon() + "] " + super.toString() + " (At:" + At + ")";
    }
}
