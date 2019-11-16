/**
 * task class to initiate a "tick" or "cross" for every task
 */

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718");
    }

    public String toString() {
        return getStatusIcon() + this.description;
    }
    public void setDone() {
        this.isDone = true;
    }
    // public String setDelete() {
    //   return "Noted. I've removed this task:\n\t " + toString();
    //  }
}