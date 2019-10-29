package Task;

import Task.Task;

public class Event extends Task {

    String date;

    public Event(String description, int value, String date){
        super(description, value);
        this.date = date;
    }

    public Event(String description, String date){
        super(description, 0);
        this.date = date;
    }

    @Override
    public String toString(){
        return "[E][" + getStatusIcon() + "] " + this.description + " (at: " + this.date + ")";
    }
}
