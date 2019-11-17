package duke.tasklist;
/**
 * Represent a task in a list.
 *
 */
public abstract class Task {
    protected String description, printTaskPriority;
    protected boolean isDone;
    protected Priority taskPriority;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * getStatusIcon will return ✓ if the task is done, or ✘ if it the task is not done
     */

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    /**
     * getDescription will the task Description
     */
    public String getDescription(){
        return description;
    }
    /**
     * markAsDone method is used to change the status of the task
     * @param status True if the task is done, false if the task is not done
     */
    public void markAsDone(boolean status){
        this.isDone = status;
    }
    /**
     * setTaskPriority method is used to set the priority of the task
     * @param taskPriority accepts high, medium or low as the task priority level
     */
    public void setTaskPriority(Priority taskPriority){
        this.taskPriority = taskPriority;
    }
    /**
     * getTaskPriorityToString method is used to convert Priority object to the equivalent String object
     * @return the equivalent String object for the different priority level: HIGH --> H, MEDIUM --> M, LOW --> L
     */
    public String getTaskPriorityToString(){
        printTaskPriority = Priority.PriorityToString(taskPriority);
        return printTaskPriority;
    }
    /**
     * getTaskPriority method returns the priority level of the task
     * @return the priority level of the task: HIGH, MEDIUM, LOW
     */

    public Priority getTaskPriority(){
        return taskPriority;
    }

    public abstract String saveToFile();


}
