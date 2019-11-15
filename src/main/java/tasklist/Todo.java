package tasklist;

public class Todo extends Task {

    public Todo (String description){
        super(description);
        super.isDone = false;
        super.taskPriority = Priority.LOW;
    }

    public Todo (String description, boolean isDone){
        super(description);
        super.isDone = isDone;
        super.taskPriority = Priority.LOW;
    }

    public Todo (String description, boolean isDone, Priority taskPriority){
        super(description);
        super.isDone = isDone;
        super.taskPriority = taskPriority;
    }

    @Override
    public String getDescription() {
        return "[T]" + "[" + getStatusIcon() + "][" + super.getTaskPriorityToString() + "]" + super.getDescription();
    }

    public String saveToFile(){
        int taskStatus = isDone ? 1:0;
        return "T | " + taskStatus + " | " + super.getTaskPriorityToString() + " | " + super.getDescription() + "\r";
    }

}
