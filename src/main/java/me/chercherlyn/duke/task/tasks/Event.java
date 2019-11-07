package me.chercherlyn.duke.task.tasks;

import me.chercherlyn.duke.task.Task;

public class Event extends Task {

    private String timeAt;

    public Event(String description, String at) {
        super(description);
        this.timeAt = at;
    }

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
