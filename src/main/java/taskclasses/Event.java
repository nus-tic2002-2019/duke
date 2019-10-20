package taskclasses;

public class Event extends Task{
    public Event(String description, String date, String time, String datetime){
        super(description, "E");
        Time = time;
        Date = date;
        DateTime = datetime;
    }
}
