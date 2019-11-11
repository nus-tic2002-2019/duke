package com.duke.task;

/**
 * Represent a todoTask
 */
public class Todo extends Task{
    private static final String TASK_TYPE="T";

    /**
     * Constructs a todoTask with description
     * by default the isDone is false
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a todoTask with description and isDone
     */
    public Todo(String description, boolean isDone) {
        super(description,isDone);
    }

    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    @Override
    public boolean equals(Object obj) {

        if (! super.equals(obj)) return false;
        if (this.getClass() != obj.getClass())
            return false;
        else return true;

    }



    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
