package tasks;

/**
 * Todo class is a child of Task class and
 * represent tasks with only task description
 */

public class Todo extends Task {
    private boolean type = false;

    /**
     * constructor for Todo class
     * @param description task description
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getDate(){
        return "";
    }

    @Override
    public String getTime() {
        return "";
    }
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

}
