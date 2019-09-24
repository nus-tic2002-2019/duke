package main.java;

public class Task {
    private boolean isDone;
    private String description;
    public static final char checkmark = '\u2713';
    public static final char crossmark = '\u274C';

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task(String description) {
        setDescription(description);
        setDone(false);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", (isDone) ? checkmark : crossmark, description);
    }
}
