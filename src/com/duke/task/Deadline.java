package com.duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * represent a deadline task
 */
public class Deadline extends Task{

    protected LocalDateTime by;
    private static final String TASK_TYPE="D";

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by=by;
    }

    public Deadline(String description, LocalDateTime by, boolean isDone){
        super(description,isDone);
        this.by=by;
    }

    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    public LocalDateTime getTaskTime() {
        return by;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
