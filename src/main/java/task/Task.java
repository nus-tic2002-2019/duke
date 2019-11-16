//level 7.more oop
/**
 *  Task command for storing Task List
 */

package task;

import java.time.LocalDateTime;

public class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDateTime date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void taskDone() {
        this.isDone = true;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "[" + (isDone? "√" : "X") + "] " + description;

    }


}

/**
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone(boolean isDone) {
        this.isDone = isDone;
    }

//    public boolean isDone() {
 ////        return isDone;
 ////    }


    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols

    }


    public String getDescription() {
        return description;
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    //need to save
    public String SaveFile(){
        Integer intIsDone = isDone ? 1:0;
        return " | " + intIsDone + " | ";
    }

}
**/