package tasks;

/**
 * Task class represent tasks and activities to be completed.
 */
public class Task {
    private String task;
    private boolean done;

    /**
     * constructor for Task class
     * @param task task description
     */
    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public String getTask(){
        return task;
    }

    public boolean getDone(){
        return done;
    }

    public void isDone() {
        done = true;
    }

    public String getDate(){
        return "";
    }

    public String getTime() {
        return "";
    }

    public String getStatusIcon() {
        return (done ? "\u2713" : "\u2718");
    }

    public String toString(){
        return "[" + getStatusIcon() + "] " + getTask();
    }

    public void print(){
        System.out.println(this.toString());
    }
}
