

public abstract class Task {
    protected String storeWords;
    protected boolean isDone;
    protected TaskType taskType;

    protected int taskIndex;



    public Task(String thingsToDo){
        this.storeWords = thingsToDo;
        this.isDone = false;
    }

    public void setTaskIndex(int i){
        taskIndex = i;
    }
    public int getTaskIndex(){
        return taskIndex;
    }

    public String getTask(){
        return storeWords;
    }
    public boolean getIsDone(){
        return isDone;
    }
    public void edit_done(boolean is_it_done){
        isDone = is_it_done;
    }
    public void edit_task(String thingsToDo){
        storeWords = thingsToDo;
    }
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    public String toString() {
        return " [" + this.getStatusIcon() + "] " + this.getTask();
    }
    public TaskType getTaskType(){ return taskType; }




}