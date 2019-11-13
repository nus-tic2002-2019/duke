import java.time.LocalDate;

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
