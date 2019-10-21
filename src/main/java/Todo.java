public class Todo extends Task{
    protected String to;

    /**
     * @param description Display the description of the user input of Todo
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * @return
     */
    public String saveTask() {
        int isDone;
        if(super.isDone)
            isDone = 1;
        else
            isDone = 0;
        return "T | " + isDone + " | " + super.saveTask();
    }
}

