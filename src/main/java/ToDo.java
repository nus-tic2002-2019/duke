/*
 *  ToDo.java
 *  Defines the ToDo task.
 */
public class ToDo extends Task {

    protected String by;

    /*
     * Constructs ToDo object
     * @param description Task description.
     */
    public ToDo(String description) {
        super(description);
    }

    /*
     * Constructs Todo object
     * @param description Task description.
     * @param status Task's completion status.
     */
    public ToDo(String status, String description){
        super(description);
        this.setStatus(status);
    }

    /*
     * Returns ToDo task
     * @return 'T' ToDo task.
     */
    @Override
    public char getTaskType() {
        return 'T';
    }

    /*
     * Returns ToDo message
     * @return details of ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


}