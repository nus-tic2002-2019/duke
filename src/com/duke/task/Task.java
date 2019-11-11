package com.duke.task;

import java.time.LocalDateTime;

/**
 * represent a task in the taskList
 */
public class Task {
    protected String description;
    protected boolean isDone;
    private LocalDateTime taskTime=LocalDateTime.of(2000,1,1,0,0);
    private static final String TASK_TYPE="A";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description,boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * get the displayed icon from isDone status
     * @return a string icon to display the status
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * mask a task as done status.
     */
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
    public boolean equals(Object obj) {

        Task otherTask = (Task) obj;
        return obj == this // short circuit if same object
                || (obj instanceof Task // instanceof handles nulls
                && otherTask.description.equals(this.description)
                && otherTask.isDone == this.isDone
                && otherTask.taskTime.equals(this.taskTime));
    }

    @Override
    public String toString(){
        return "["+getStatusIcon()+"] "+getDescription();
    }
}
