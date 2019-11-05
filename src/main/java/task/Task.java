package task;

public class  Task {
    protected String description;
    protected boolean isDone;
    protected char type;

    public Task(String description){
        this.description = description.trim();
        this.isDone = false;
    }

    public String getTask(){
        return description;
    }

    public void setTaskDone(){
        this.isDone = true;
    }

    public String getTaskStatus(){
       return "[" + (isDone ? "\u2713" : "\u2718") + "]";
    }

    public String getType(){
        return "[" + this.type + "]";
    }

    public String getDetails(){
        return "" ;
    }

    public String getDescription() {return this.description; }
}
