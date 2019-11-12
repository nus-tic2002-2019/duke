package task;

import java.time.LocalDateTime;

/**
 * Represent the data structure to manage user's task list.
 */
public class Task{

    protected String description;
    protected boolean isDone;
    protected LocalDateTime date;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    //public String getStatusIcon(){
    //    return (isDone ? "√" : "X"); //return tick or X symbols
    //}

    public String getDescription(){
        return description;
    }

    public LocalDateTime getDate(){
        return date;
    }

    public void taskDone(){
        this.isDone = true;
    }

    @Override
    public String toString(){
        return "[" + (isDone ? "√" : "X") + "]"+ description ;
    }
}

