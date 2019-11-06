package me.chercherlyn.duke.task;

public abstract class Task {

    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    // getters
    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    // getters func
    public String getStatusIcon() {
        //return tick or X symbols
        return (done ? "\u2713" : "\u2718");
    }

    // setters
    public void setDone(boolean done) {
        this.done = done;
    }
}
