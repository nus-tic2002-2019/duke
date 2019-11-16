public class Task {
    protected String description;
    protected boolean isDone;
    protected String by;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.by = by;
    }

    public String isDone() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    //...
}