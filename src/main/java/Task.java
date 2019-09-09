package DukeClasses; 

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
        return (isDone ? "✓" : "✗"); //return tick or X symbols
    }
}