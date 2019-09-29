package main.java;

public class Event extends Task{
    private String start_endTime;

    public String getStart_endTime() {
        return start_endTime;
    }

    public void setStart_endTime(String start_endTime) {
        this.start_endTime = start_endTime;
    }

    public Event(String description, String start_endTime) throws DukeMissingDescException {
        super(description);
        setStart_endTime(start_endTime);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), getStart_endTime());
    }
}
