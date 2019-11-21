package duke.task;

import duke.priority.Priority;

public abstract class Task{
    protected String description;
    private boolean isDone;
    private Priority p;
    public Task(String description, Priority p){
        assert description != "": "description cannot be empty";
        this.description = description;
        this.isDone=false;
        this.p = p;
    }
    
    public void markAsDone (){
        this.isDone = true;
    }
    
    public void markAsUndone(){
        this.isDone = false;
    }

    public boolean isDone (){
      return this.isDone;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public Priority getp(){
        return this.p;
    }
    
    public void setPriority(Priority p){
        this.p = p;
    }
    
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    
    public int getStatus() {
        return (isDone ? 1 : 0); //return 1 or 0
    }
    
    public abstract String getDateTime();
    public abstract String printTask();
    public abstract String writeTask();
}
