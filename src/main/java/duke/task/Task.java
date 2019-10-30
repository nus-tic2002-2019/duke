package duke.task;

import java.util.List;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone(){
        isDone = true;
    }

    //display tick or x symbols
    public String getStatusIcon(){

        return (isDone ? "\u2713" : "\u2718");
    }

    public List<String> getList(){
        return List.of(isDone ? "1" : "0", description);
    }

    public String toString(){
        return "[" + getStatusIcon() + "]" + description;
    }

}
