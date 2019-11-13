package me.chercherlyn.duke.task.tasks;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import me.chercherlyn.duke.DukeException;
import me.chercherlyn.duke.task.Task;

/**
 * Represents deadline task.
 */
public class Deadline extends Task {
    
    private ZonedDateTime time;
    
    public Deadline(String description, String time) {
        super(description);
        
        // parse time, using input formatter
        try {
            this.time = ZonedDateTime.parse(time, DATETIME_IN_FORMATTER);
        } catch (Exception ex) {
            throw new DukeException(String.format(
                    "Date format should be in format: '%s'",
                    DATETIME_IN_PATTERN));
        }
    }
    
    public Deadline(String description, long timeMillis) {
        super(description);
        this.time = ZonedDateTime.ofInstant(
                Instant.ofEpochMilli(timeMillis),
                ZoneId.systemDefault());
    }
    
    /**
     * Returns a time deadline will end by.
     *
     * @return time
     */
    public long getTimeMillis() {
        return time.toInstant().toEpochMilli();
    }
    
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                getStatusIcon(),
                getDescription(),
                DATETIME_OUT_FORMATTER.format(time));
    }
}
