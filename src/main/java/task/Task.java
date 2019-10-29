package task;

public class Task {

    protected String description;
    protected boolean isDone = false;
    protected int priority = 0;

    /**
     * Constructor of Task
     * @param description
     * @param value
     */
    public Task(String description, int value) {
        this.description = description;
        this.isDone = (value == 1 ? true : false);
    }

    /**
     * Return the status of the Task
     * @return String
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Getter of the Task's description
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter of the Task's status
     */
    public void setIsDone(){
        this.isDone = true;
        System.out.println("Nice!! I've marked this task as done: \n" + "[" + this.getStatusIcon() + "] " + this.description);
        System.out.printf("\n");
    }

}
