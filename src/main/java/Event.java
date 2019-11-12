
public class Event extends Task {
    String t;
    public Event(String description, String time)
    {
        super(description);
        t = time;
    }

    public String getStatusIcon() {

        return ("[E]" + super.getStatusIcon()); //return tick or X symbols
    }

    public String getDescription() {

        return (super.getDescription()+" (at: "+t+")");
    }

}
