import java.util.Scanner;

public class DukeTask extends Duke {

    protected String description;
    protected boolean isDone;

    public DukeTask (String description, boolean isDone){
        this.description = description;
        this.isDone = false;
    }

    public void setDescription (String description){
        this.description = description;
    }

    public boolean getisDone(){
        return isDone;
    }

    public void setisDone(boolean isDone){
        this.isDone = isDone;
    }

    public String toString(){
        return "[" + getStatusIcon() + "]" + getisDone();
    }

    public String getStatusIcon(){
        // return tick or X symbol
        return isDone ? "\u2713" : "\u2718";

    }

    public void markAsDone(){
       isDone = true;
    }
}