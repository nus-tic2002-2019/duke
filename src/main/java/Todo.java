public class Todo extends Task {

    public Todo (String taskName, boolean taskDone)
    {
        super(taskName, taskDone); // calls the parent constructor
    }

    public String toString()
    {
        return "[T]" + super.toString();
    }
}
