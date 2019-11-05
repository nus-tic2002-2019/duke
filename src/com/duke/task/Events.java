package com.duke.task;

public class Events extends Task {
    protected String at;
    private static final String TASK_TYPE="E";
    public Events(String description,String at) {
        super(description);
        this.at=at;
    }

    public Events(String description,String at, boolean isDone){
        super(description,isDone);
        this.at=at;
    }

    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    @Override
    public String getTaskTime() {
        return at;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
