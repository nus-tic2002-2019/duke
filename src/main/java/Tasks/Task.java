package Tasks;

import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * empty task constructo
     */
    public Task(){

    }

    //Getters
    /**
     * Getters
     * Returns the description of the task
     *
     * @return The description of the task in string.
     */
    public String getDescription(){
        return getStatus() + getStatusIcon() + " " + this.description;
    }

    //Setters

    public String getStatusIcon() {
        return (isDone ? "[" + "\u2713" + "]": "["+"\u2718"+"]"); //return tick or X symbols
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public String getStatus(){return "";};


    public String toString() {
        return this.getStatusIcon() + this.description;
    }

}
