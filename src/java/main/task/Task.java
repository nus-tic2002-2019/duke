/*
Task.java
Define Task.
*/

package task;

public class Task {
    protected String description;
    protected boolean isDone;

    /*
    constructor of task object.
    @param description task description
    */

    public Task(String description) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");//return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String save() {
        return this.getDescription();
    }
}



