package me.chercherlyn.duke.task.tasks;

import me.chercherlyn.duke.task.Task;

/**
 * Represents event task.
 */
public class Event extends Task {
    
    private String timeAt;
    
    public Event(String description, String at) {
        super(description);
        this.timeAt = at;
    }
    
    /**
     * Returns a time event will pass at.
     *
     * @return time
     */
    public String getTimeAt() {
        return timeAt;
    }
    
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                getStatusIcon(),
                getDescription(),
                timeAt);
    }
}
