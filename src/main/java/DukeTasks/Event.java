package newDuke.DukeTasks; 
/**
 * A type of Task that includes a date attribute for the date in which the Task takes place.
 */
public class Event extends Task {
	
    protected String at;
	/**
     * Constructor for an Event Task.
     *
     * @param description A String representing the name of the Task.
     * @param at A String which represents the venue of the Task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
		this.setSymbol("E");
    }

    @Override
    public String toString() {
        return "["+this.getSymbol()+"]" + this.getStatusIcon() +" "+ super.toString() + " (at: " + at + ")";
    }
	@Override
	public String toSave() {
        return this.getSymbol() + " | " + this.getStatus() + " | " + super.toString() + " | " + at;
    }
}
