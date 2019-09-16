public class Task {
    protected String description;
    protected boolean isDone;
    protected char type;

    public Task(String description){
        this.description = description;
        this.isDone = false;
        this.type = '?';
    }

    public Task() {
        this.description = "empty task";
        this.isDone = false;
        this.type = '?';
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
}
