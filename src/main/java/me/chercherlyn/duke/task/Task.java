package me.chercherlyn.duke.task;

import me.chercherlyn.duke.task.tasks.Deadline;
import me.chercherlyn.duke.task.tasks.Event;
import me.chercherlyn.duke.task.tasks.Todo;

public abstract class Task {

    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    // getters
    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    // getters func
    public String getStatusIcon() {
        //return tick or X symbols
        return (done ? "\u2713" : "\u2718");
    }

    // setters
    public void setDone(boolean done) {
        this.done = done;
    }

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
                String time = dataArr[3];
                task = new Event(description, time);
                task.setDone(done);
                return task;
            case "D":
                time = dataArr[3];
                task = new Deadline(description, time);
                task.setDone(done);
                return task;
            default:
                throw new IllegalStateException("Invalid task type!");
        }
    }

    public static String serialize(Task task) {
        if (task instanceof Todo) {
            return String.format("T | %s | %s",
                    task.isDone() ? "1" : "0",
                    task.getDescription());
        } else if (task instanceof Event) {
            return String.format("E | %s | %s | %s",
                    task.isDone() ? "1" : "0",
                    task.getDescription(),
                    ((Event) task).getTimeAt());
        } else if (task instanceof Deadline) {
            return String.format("D | %s | %s | %s",
                    task.isDone() ? "1" : "0",
                    task.getDescription(),
                    ((Deadline) task).getTimeBy());
        } else throw new IllegalStateException("Uknown task type!");
    }

}
