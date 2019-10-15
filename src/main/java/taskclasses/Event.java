package taskclasses;

public class Event extends Task{
    public Event(String description, String Event_time){
        super(description, "E");
        Time = Event_time;
    }
}
