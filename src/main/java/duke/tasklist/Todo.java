package duke.tasklist;

public class Todo extends Task {

    public Todo (String description, boolean isDone, Priority taskPriority){
        super(description);
        super.isDone = isDone;
        super.taskPriority = taskPriority;
    }

    @Override
    public String getDescription() {
        return "[T]" + "[" + getStatusIcon() + "][" + super.getTaskPriorityToString() + "]" + super.getDescription();
    }
    @Override
    public String saveToFile(){
        int taskStatus = isDone ? 1:0;
        return "T | " + taskStatus + " | " + super.getTaskPriorityToString() + " | " + super.getDescription() + "\r";
    }

}
