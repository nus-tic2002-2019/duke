package duke.task;
/**
 * The parent class of Deadline, Event and Todo.
 * */
public class Task {
    protected String description;
    protected boolean isDone;
    /**
     * Creation of new Task with description.
     * @param description description of Task.
     * */
    public Task(String description){
        this.description = description;
        this.isDone = false;
    }
    /**
     * Mark task as done.
     * */
    public void markAsDone(){
        isDone = true;
    }
    /**
     * Get task icon for whether the task is done.
     * @return icon.
     * */
    public String getStatusIcon(){
        return (isDone ? "\u2713" : "\u2718");
    }
    /**
     * Convert task to string in printing format.
     * @return printing format.
     * */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
    /**
     * Covert task to string in saving format.
     * @return saving format.
     * */
    public String save_toString() {
        return getStatusIcon() + " | " + description;
    }
}