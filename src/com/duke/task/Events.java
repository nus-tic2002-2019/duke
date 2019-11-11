package com.duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * represent a event task
 */
public class Events extends Task {
    protected LocalDateTime at;
    private static final String TASK_TYPE="E";

    /**
     * Constructs a event task with description and time
     * by default the isDone is false
     */
    public Events(String description,LocalDateTime at) {
        super(description);
        this.at=at;
    }

    /**
     * Constructs a event task with description, time and isDone
     */
    public Events(String description,LocalDateTime at, boolean isDone){
        super(description,isDone);
        this.at=at;
    }

    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    @Override
    public LocalDateTime getTaskTime() {
        return at;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
