package com.duke.task;

public class Deadline extends Task{

    protected String by;
    private static final String TASK_TYPE="D";

    public Deadline(String description,String by) {
        super(description);
        this.by=by;
    }

    public Deadline(String description,String by, boolean isDone){
        super(description,isDone);
        this.by=by;
    }

    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    public String getTaskTime() {
        return by;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
