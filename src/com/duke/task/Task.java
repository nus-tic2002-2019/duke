package com.duke.task;

import java.time.LocalDateTime;

public class Task {
    protected String description;
    protected boolean isDone;
    private LocalDateTime taskTime;
    private static final String TASK_TYPE="A";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description,boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone(){
        isDone=true;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone(){
        return isDone;
    }

    public String getTaskType(){
        return TASK_TYPE;
    }
    public LocalDateTime getTaskTime(){
        return taskTime;
    }

    @Override
    public String toString(){
        return "["+getStatusIcon()+"] "+getDescription();
    }
}
