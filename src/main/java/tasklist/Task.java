package tasklist;

import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Task implements Serializable {
    public ObjectOutputStream objectOut;
    protected String description;
    protected boolean isDone;
    public String Type = "Task";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean status) {
        this.description = description;
        this.isDone = status;
    }


    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone(){
        isDone = true;
        getStatusIcon();
    }

    public String toString() {

        return ("[" + this.getStatusIcon() + "] " + description);
    }

    public String saveFormat(){
        if (this.isDone == Boolean.parseBoolean("true")){
            return ("| 1 | " + this.description);
        }
        else {
            return ("| 0 | " + this.description);
        }
    }
}
