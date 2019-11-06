package me.chercherlyn.duke.task.tasks;

import me.chercherlyn.duke.task.Task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s",
                getStatusIcon(),
                getDescription());
    }
}
