package task;

import java.util.Date;
/**
 * Represents the deadline class that initiated by task.
 * */
public class Deadline extends ToDo {
    /**
     * Creation of date variable
     */
    protected Date by;
    /**
     * Checks description input and see if date provided is not earlier than current date,
     * then returns the respective description and date.
     * */
    public Deadline(String s, Date by){
        super(s);
        Date now=new Date();
        assert by.after(now):"task.Deadline cannot be earlier than now!";
        this.by = by;
    }
    /**
     * Prints icon according to respective action.
     * */
    public String list(){
        return "[D]"+super.print()+"do by: "+by;
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
