package newDuke.DukeTasks; 
import java.time.LocalDate;

// Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
public class Deadline extends Task {
    protected String by;
	
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
	@Override
	public String getDate(){
		return by;
	}
	
}
