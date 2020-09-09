package me.chercherlyn.duke.task.tasks;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import me.chercherlyn.duke.DukeException;
import me.chercherlyn.duke.task.Task;

/**
 * Represents event task.
 */
public class Event extends Task {
    
    private ZonedDateTime time;
    
    public Event(String description, String time) {
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
    
    public Event(String description, long timeMillis) {
        super(description);
        this.time = ZonedDateTime.ofInstant(
                Instant.ofEpochMilli(timeMillis),
                ZoneId.systemDefault());
    }
    
    /**
     * Returns a time event will pass at.
     *
     * @return time
     */
    public long getTimeMillis() {
        return time.toInstant().toEpochMilli();
    }
    
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                getStatusIcon(),
                getDescription(),
                DATETIME_OUT_FORMATTER.format(time));
    }
}
