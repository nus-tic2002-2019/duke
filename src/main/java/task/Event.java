package task;

import java.util.Date;
/**
 * Represents the event class that initiated by task.
 * */
public class Event extends ToDo {
    /**
     * Creation of date variable
     */
    protected Date at;
    /**
     * Checks description input and see if date provided is not earlier than current date,
     * then returns the respective description and date.
     * */
    public Event(String description, Date at) {
        super(description);
        Date now=new Date();
        assert at.after(now):"task.Deadline cannot be earlier than now!";
        this.at = at;
    }
    /**
     * Prints icon according to respective action.
     * */
    public String list(){
        return "[E]" +super.print()+ "at: "+at;
    }
    /**
     * Changes activity icon from cross to tick.
     * */
    public void setDone(boolean isDone){
        super.setDone(isDone);
    }
    /**
     * Retrieves boolean from parent class task.
     * */
    public boolean getDone(){
        return super.getDone();
    }
    /**
     * Retrieves string of text from parent class task.
     * */
    public String getText(){
        return super.getText();
    }
}
