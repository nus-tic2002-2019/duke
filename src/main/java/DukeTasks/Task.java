package newDuke.DukeTasks; 

/**
 * An abstract Class extended by all the Task type classes.
 *
 * <p></p>Each Task contains a description, a symbol and an isDone attribute. Two abstract methods (toString(), toSave() and getDate()) are meant
 * to be implemented by any classes that extend the Task abstract class.
 */
public abstract class Task {
	protected String description;
	protected boolean isDone;
	protected String symbol;

	public Task(String description) {
	this.description = description;
	this.isDone = false;
	}
	
	/**
	 * Returns the description of the Task.
	 *
	 * @return String which is the description of the Task.
	 */
	public String description() {
	return description;
	}
	
	/**
	 * Returns the status icon of the Task.
	 *
	* @return String which is the status icon of the Task.
	*/
	public String getStatusIcon() {
	return (isDone ? "[V]" : "[X]"); //return tick or X symbols
	}
	/**
	* Returns the done status (true/false) of the Task.
	*
	* @return String which is the done status (true/false) of the Task.
	*/
	public int getStatus() {
	return (isDone ? 1 : 0); //return 1 or 0 symbols
	}
	/**
	* Indicates that this Task has already been completed.
	*/
	public void setStatusIconTrue() {
	this.isDone = true;
	}
	/**
	* Indicates that this Task has not been completed. 
	 * For future use cases or regression that might require status to be set to false.
	*/
	public void setStatusIconFalse() {
	this.isDone = false;
	}
	/**
	* Returns the Task type (T/E/D) of the Task.
	*
	* @return String which is the Task type (T/E/D) of the Task.
	*/
	public String getSymbol() {
	return symbol;
	}
	/**
	* Sets the Task type (T/E/D).
	*/
	public void setSymbol(String symbol) {
	this.symbol = symbol;
	}
	/**
	* Returns a String description of the Task
	*
	* @return A String description of the Task.
	*/
	@Override
	public String toString() {
	return description;
	}
	/**
	* Returns a String representing the Task in a format to be saved into the hard disk's storage file.
	*
	* <p></p>To be implemented by each individual Task class.
	*
	* @return A String representation of this Task formatted for the storage file.
	*/
	public abstract String toSave();
}
