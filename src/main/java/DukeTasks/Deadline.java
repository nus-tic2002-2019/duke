package newDuke.DukeTasks; 

/**
 * A type of Task that includes a date attribute for the Task to be completed by. Also includes two SimpleDateFormatter.
 */
public class Deadline extends Task {
    protected String by;
    /**
     * Constructor for a Deadline Task.
     *
     * @param description A String which represents the name of the Task.
     * @param by A String which represents the date and time of the Task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
		this.setSymbol("D");
    }
    @Override
    public String toString() {
        return "["+this.getSymbol()+"]" + this.getStatusIcon() +" "+ super.toString() + " (by: " + by + ")";
    }
	
    @Override
    public String toSave() {
    	return this.getSymbol() + " | " + this.getStatus() + " | " + super.toString() + " | " + by;
    }
	
   /**
     * Returns a String date of the Task.
     *
     * <p></p>To be implemented by Deadline Task class.
     *
     * @return A String that returns the date of Deadline.
     */
     public String getDate(){
	return by;
     }
	
}
