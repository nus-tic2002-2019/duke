package main.taskLists;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void Done() {
        this.isDone = !this.isDone;
    }

    public void setStatus(Boolean input) {
        this.isDone = input;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    ;

    @Override
    public String toString() {
        return this.description;
    }

}