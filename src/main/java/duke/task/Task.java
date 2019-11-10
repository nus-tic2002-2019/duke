package duke.task;

import java.util.List;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Display the status icon of this task (tick or x symbols) to the user.
     *
     * @return status icon.
     */
    public String getStatusIcon() {

        //return (isDone ? "\u2713" : "\u2718");
        return (isDone ? "✓" : "✘");
    }

    /**
     *Return a list of strings that can be saved.
     *
     * @return a task list for saving.
     */
    public List<String> getList() {
        return List.of(isDone ? "1" : "0", description);
    }

    /**
     *Return a list of strings to user.
     *
     * @return this string task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "]" + description;
    }

}
