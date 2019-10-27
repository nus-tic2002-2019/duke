import Task.*;

public class Event extends Task {

    protected boolean isEvent;
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        isEvent = false;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() +  "(at:" + at  + ")" ;
    }

}
