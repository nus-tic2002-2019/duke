package Tasks;

import Tasks.Task;
import Tasks.TaskType;

public class ToDos extends Task {


    public ToDos(String thingsToDo) {
        super(thingsToDo);
        taskType = TaskType.TODOS;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

   
}
