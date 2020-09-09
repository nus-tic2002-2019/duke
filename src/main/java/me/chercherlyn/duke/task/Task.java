package me.chercherlyn.duke.task;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import me.chercherlyn.duke.task.tasks.Deadline;
import me.chercherlyn.duke.task.tasks.Event;
import me.chercherlyn.duke.task.tasks.Todo;

/**
 * Represents a task
 */
public abstract class Task {
    
    protected static final String DATETIME_IN_PATTERN = "dd.MM.yyyy HHmm";
    protected static final String DATETIME_OUT_PATTERN = "dd.MM.yyyy HH:mm a";
    
    protected static final DateTimeFormatter DATETIME_IN_FORMATTER;
    protected static final DateTimeFormatter DATETIME_OUT_FORMATTER;
    
    static {
        DATETIME_IN_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_IN_PATTERN)
                .withZone(ZoneId.systemDefault());
        DATETIME_OUT_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_OUT_PATTERN)
                .withZone(ZoneId.systemDefault());
    }
    
    private String description;
    private boolean done;
    
    public Task(String description) {
        this.description = description;
        this.done = false;
    }
    
    // getters
    
    /**
     * Returns task description
     *
     * @return task description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Returns whether task marked as done or not
     *
     * @return true if marked done, false otherwise
     */
    public boolean isDone() {
        return done;
    }
    
    // getters func
    
    /**
     * Returns fancy status icon, depending on done status
     *
     * @return "\u2713" if done, "\u2718" if not
     */
    public String getStatusIcon() {
        return (done ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    
    // setters
    
    /**
     * Sets marked as done status
     *
     * @param done done status
     */
    public void setDone(boolean done) {
        this.done = done;
    }
    
    // static
    
    /**
     * Deserializes task from string data
     *
     * @param data data
     * @return task
     */
    public static Task deserialize(String data) {
        String[] dataArr = data.split(" \\| ");
        
        String type = dataArr[0];
        boolean done = Integer.parseInt(dataArr[1]) == 1;
        String description = dataArr[2];
        
        Task task;
        
        switch (type) {
            case "T":
                task = new Todo(description);
                task.setDone(done);
                return task;
            case "E":
                long timeMillis = Long.parseLong(dataArr[3]);
                task = new Event(description, timeMillis);
                task.setDone(done);
                return task;
            case "D":
                timeMillis = Long.parseLong(dataArr[3]);
                task = new Deadline(description, timeMillis);
                task.setDone(done);
                return task;
            default:
                throw new IllegalStateException("Invalid task type!");
        }
    }
    
    /**
     * Serializes task to string data
     *
     * @param task task
     * @return data
     */
    public static String serialize(Task task) {
        if (task instanceof Todo) {
            return String.format("T | %s | %s",
                    task.isDone() ? "1" : "0",
                    task.getDescription());
        } else if (task instanceof Event) {
            return String.format("E | %s | %s | %d",
                    task.isDone() ? "1" : "0",
                    task.getDescription(),
                    ((Event) task).getTimeMillis());
        } else if (task instanceof Deadline) {
            return String.format("D | %s | %s | %d",
                    task.isDone() ? "1" : "0",
                    task.getDescription(),
                    ((Deadline) task).getTimeMillis());
        } else throw new IllegalStateException("Uknown task type!");
    }
}