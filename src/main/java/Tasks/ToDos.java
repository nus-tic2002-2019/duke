package Tasks;

/**
 * The ToDo class for user to store their To Do task
 */
public class ToDos extends Task {

    /**
     * Constructs and store the task that the user input
     * @param thingsToDo The task that the user input to do
     */
    public ToDos(String thingsToDo) {
        super(thingsToDo);
        taskType = TaskType.TODOS;
    }

    /**
     * When this function is called, it will return the task type with the task that the user input and whether it is done or not
     * @return
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

   
}
