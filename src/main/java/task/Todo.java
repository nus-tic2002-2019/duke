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
     * Constructor of Todo with all attributes
     *
     * @param description
     * @param value
     * @param done
     *
     */
    public Todo(String description, int done, int value) {
        super(description, done);
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

    /**
     * Setter of priority value
     * @param value
     */
    public void setPriority(int value){
        this.priority = value;
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + this.description + "(Priority: " + priority + ")";
    }
}