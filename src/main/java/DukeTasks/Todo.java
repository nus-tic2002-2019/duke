package DukeTasks; 

// ToDos: tasks without any date/time attached to it e.g., visit new theme park
public class Todo extends Task {
    protected String by;

    public Todo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + this.getStatusIcon() +" "+ super.toString();
    }
}