package DukeTasks; 

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
	public String description() {
        return description;
    }
	
    public String getStatusIcon() {
        return (isDone ? "[V]" : "[X]"); //return tick or X symbols
    }
	public void setStatusIconTrue() {
        this.isDone = true;
    }
	public void setStatusIconFalse() {
        this.isDone = false; // regression if required
    }
	@Override
    public String toString() {
        return description;
    }
}

// ToDos: tasks without any date/time attached to it e.g., visit new theme park
class Todo extends Task {
    protected String by;

    public Todo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + this.getStatusIcon() +" "+ super.toString();
    }
}
// Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + this.getStatusIcon() +" "+ super.toString() + " (by: " + by + ")";
    }
}
// Events: tasks that start at a specific time and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm
class Events extends Task {
    protected String at;

    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + this.getStatusIcon() +" "+ super.toString() + " (at: " + at + ")";
    }
}
