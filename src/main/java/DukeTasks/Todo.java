package newDuke.DukeTasks; 

/**
 * A simple Task which only indicates what task needs to be completed.
 */
public class Todo extends Task {
	/**
     * Constructor for a Todo Task.
     *
     * @param description A String representing the name of the Task.
     */
    public Todo(String description) {
        super(description);
		this.setSymbol("T");
    }
	
    public String toString() {
	return "["+this.getSymbol()+"]" +this.getStatusIcon() +" "+ super.toString();
    }
	
	public String toSave() {
        return this.getSymbol() + " | " + this.getStatus() + " | " + super.toString();
    }
}
