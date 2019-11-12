package me.chercherlyn.duke.task.tasks;

import me.chercherlyn.duke.task.Task;

/**
 * Represents deadline task.
 */
public class Deadline extends Task {
    
    private String timeBy;
    
    public Deadline(String description, String by) {
        super(description);
        this.timeBy = by;
    }
    
    /**
     * Returns a time deadline will end by.
     *
     * @return time
     */
    public String getTimeBy() {
        return timeBy;
    }
    
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                getStatusIcon(),
                getDescription(),
                timeBy);
    }
}
