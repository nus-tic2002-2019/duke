public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "[\u2713] " : "[\u2718] "); //return tick or X symbols
    }

    public String toString(){
        return getStatusIcon() + this.description;
    }

    public String setDone(){
        this.isDone = true;
        return "\t____________________________________________________________\n\t Nice! I've marked this task as done:\n\t  [\u2713] "
        + this.description
        + "\n\t____________________________________________________________";
    }
}