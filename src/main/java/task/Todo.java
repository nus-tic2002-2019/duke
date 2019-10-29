package Task;

import Task.Task;

public class Todo extends Task {

    public Todo(String description, int value){
        super(description, value);
    }

    public Todo(String description){
        super(description, 0);
    }

    @Override
    public String toString(){
        return "[T][" + getStatusIcon() + "] " + this.description;
    }
}
