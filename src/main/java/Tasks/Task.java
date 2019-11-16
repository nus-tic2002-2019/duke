package Tasks;

import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;
    protected char type;
   // protected ArrayList<String> taskList = new ArrayList<>();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
    public void setDone(boolean done) {
        isDone = done;
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


//    /**
//     * Get the type of current Task, if it is a todo, event, deadline or DoAfter task.
//     * todo = 'T'
//     * event = 'E'
//     * deadline = 'D'
//     * DpAfter = 'A'
//     *
//     * @return [T/E/D/A]
//     */
//    public String getType() {
//        return "[" + this.type + "]";
//    }


    public String toString() {
        return this.getStatusIcon() + this.description;
    }

}
