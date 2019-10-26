package task;

/**
 * Represent data structure to for Todo command.
 * It takes in a description only.
 */
public class Todo extends Task{
    private String description;

    public Todo(String description){
        super(description);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
