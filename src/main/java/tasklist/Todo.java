package tasklist;

import tasklist.Task;

public class Todo extends Task {

    public Todo (String description){
        super(description);
        super.isDone = false;
    }

    public Todo (String description, boolean isDone){
        super(description);
        super.isDone = isDone;
    }

    @Override
    public String getDescription() {
        return "[T]" + "[" + getStatusIcon() + "]" + super.getDescription();
    }

    public String saveToFile(){
        int taskStatus = isDone ? 1:0;
        return "T | " + taskStatus + " | " + super.getDescription() + "\r";
    }

}
