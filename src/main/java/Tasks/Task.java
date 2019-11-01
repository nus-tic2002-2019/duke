package Tasks;

import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;
   // protected ArrayList<String> taskList = new ArrayList<>();



    //Getters

    public String getDescription(){
        return getStatus() + getStatusIcon() + " " + this.description;
    }

    //Setters
    public void setDone(boolean done) {
        isDone = done;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[" + "\u2713" + "]": "["+"\u2718"+"]"); //return tick or X symbols
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public String getStatus(){return "";};

    public void markAsUndone(){
        this.isDone = false;
    }

    public String toString() {
        return this.getStatusIcon() + this.description;
    }

}
