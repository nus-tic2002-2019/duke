package tasklist;

public class Todo extends Task
{
    protected boolean isDone;

    /**
     * Todo constructor
     * @param description String todo description
     */
    public Todo(String description)
    {
        super(description);
        isDone=false;
    }

    /**
     * To mark this deadline as completed
     * @param done mark the status as completed
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * To get status of the todo object
     * @return boolean status of deadline true/false
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * To geenerate todo object in readable format and display it to the user.
     *
     * @return String human readbable format of todo object
     */
    @Override
    public String toString() {
        return "[T] [" + super.getStatusIcon() +"]" + super.getDescription();
    }

    /**
     * To generate string for which to write todo  object into file over a specific format in | separated
     * @return String format of Todo objects to be written to file
     */
    @Override
    public String writeToFile()
    {
        //return "D | " + isDone + " | " + this.getDescription();
        return "T" + super.writeToFile() + super.getDescription() ;
    }
}
