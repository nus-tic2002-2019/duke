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
        return (isDone ? "[V]" : "[X]"); //return tick or X symbols
    }
	public void setStatusIconTrue() {
        this.isDone = true;
    }
	public void setStatusIconFalse() {
        this.isDone = false; // regression if required
    }
}
