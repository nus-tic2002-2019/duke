import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Events extends Task {


    public Events(String thingsToDo, LocalDate at) {
        super(thingsToDo);
        this.taskDate = at;
        taskType = TaskType.EVENTS;
    }

    @Override
    public LocalDate getDate() throws DukeException{
        return taskDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + taskDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
