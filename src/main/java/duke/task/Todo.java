package duke.task;

/**
 * The ToDo class extends the Task class to format the string output of ToDo tasks.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return " [" + getStatusIcon() + "]" + "[T] " + getDescription();
    }
}
