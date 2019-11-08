package task;

public class  Task {
    protected String description;
    protected boolean isDone;
    protected char type;

    public Task(String description){
        this.description = description.trim();
        this.isDone = false;
    }

    /**
     * Returns the description of the task
     * @return The description of the task in string.
     */
    public String getTask(){
        return description;
    }

    /**
     * Sets a task done.
     */
    public void setTaskDone(){
        this.isDone = true;
    }

    /**
     * Get the task status, whether is the current task done or not.
     * @return a Tick Symbol if it is done, Cross symbol other wise.
     */
    public String getTaskStatus(){
       return "[" + (isDone ? "\u2713" : "\u2718") + "]";
    }

    /**
     * Get the type of current Task, if it is a todo, event, deadline or DoAfter task.
     * todo = 'T'
     * event = 'E'
     * deadline = 'D'
     * DpAfter = 'A'
     * @return [T/E/D/A]
     */
    public String getType(){
        return "[" + this.type + "]";
    }

    public String getDetails(){
        return "" ;
    }

    /**
     * Returns the description of the current task
     * @return A string of the current task's description.
     */
    public String getDescription() {return this.description; }
}
