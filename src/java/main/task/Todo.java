/*
Todo.java
define Todo task.
*/

package task;

public class Todo extends Task {
    protected boolean isDone;

    /*
    constructor of todo object
    @param description todo task description
     */
    public Todo(String description) {
        super(description);
        isDone = false;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "[T] [" + super.getStatusIcon() + "]" + super.getDescription();
    }

    @Override
    public String save() {
        return "T" + " | " + (this.isDone() ? 1 : 0) + " | " + super.save() + System.lineSeparator();
    }
}