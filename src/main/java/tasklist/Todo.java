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

}
