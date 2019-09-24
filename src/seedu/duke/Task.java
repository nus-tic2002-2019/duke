public class Task {
    protected String description;
    protected boolean isDone;

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

    public void setDone(){
        this.isDone = true;
    }
}