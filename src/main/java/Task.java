public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
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
}
