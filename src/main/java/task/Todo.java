package task;

public class Todo extends Task {

    /**
     * Constructor of Todo
     *
     * @param description
     * @param value
     */
    public Todo(String description, int value) {
        super(description, 0);
        this.priority = value;
    }

    /**
     * Constructor of Todo
     *
     * @param description
     */
    public Todo(String description) {
        super(description, 0);
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + this.description + "(Priority: " + priority + ")";
    }
}