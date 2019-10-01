package task;

import java.util.Date;

public class Event extends ToDo {

    protected Date at;

    public Event(String description, Date at) {
        super(description);
        Date now=new Date();
        assert at.after(now):"task.Deadline cannot be earlier than now!";
        this.at = at;
    }
    public String list(){
        return "[E]" +super.print()+ "at: "+at;
    }
    public void setDone(boolean isDone){
        super.setDone(isDone);
    }
    public boolean getDone(){
        return super.getDone();
    }

    public String getText(){
        return super.getText();
    }
}
