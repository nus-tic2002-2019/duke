package Tasks;

/**
 * The task class to store all the class
 */
public abstract class Task {
    protected String storeWords;
    protected boolean isDone;
    protected TaskType taskType;
    protected int taskIndex;

    /**
     * Constructs the task class to store the task the user requested
     * @param thingsToDo
     */
    public Task(String thingsToDo){
        this.storeWords = thingsToDo;
        this.isDone = false;
    }

    /**
     * Set the task Index
     * @param i The task index of the task
     */
    public void setTaskIndex(int i){
        taskIndex = i;
    }

    /**
     * Return the task index of the task
     * @return the task index
     */
    public int getTaskIndex(){
        return taskIndex;
    }

    /**
     * Return the task that the user set
     * @return the task that was store
     */
    public String getTask(){
        return storeWords;
    }

    /**
     * Return the task done status
     * @return the task done status
     */
    public boolean getIsDone(){
        return isDone;
    }

    /**
     * Edit the status of the task
     * @param isItDone the new status of the task
     */
    public void editDone(boolean isItDone){
        isDone = isItDone;
    }

    /**
     * Edit the task the user store
     * @param thingsToDo the new task that replaces the current task
     */
    public void editTask(String thingsToDo){
        storeWords = thingsToDo;
    }

    /**
     * Return a Tick or X symbols
     * @return a Tick or X symbols
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Convert the task description to the string
     * @return the task descruption to string
     */
    public String toString() {
        return " [" + this.getStatusIcon() + "] " + this.getTask();
    }

    /**
     * return the task type
     * @return the task type
     */
    public TaskType getTaskType(){
        return taskType;
    }




}