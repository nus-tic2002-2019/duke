package seedu.duke.data.task;

public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    /** 
     * Returns the ToDo in a String format.
     * @return String   The ToDo in a String format.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}