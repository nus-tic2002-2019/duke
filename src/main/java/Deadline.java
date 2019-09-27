import java.util.Date;

public class Deadline extends ToDo {
    protected Date by;

    public Deadline(String s, Date by){
        super(s);
        Date now=new Date();
        assert by.after(now):"Deadline cannot be earlier than now!";
        this.by = by;
    }
    public String list(){
        return "[D]" +super.print()+ "do by:"+ by;
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
