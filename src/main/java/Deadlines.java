import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadlines extends Task {


    public Deadlines(String description, LocalDate by) {
        super(description);
        this.taskDate = by;
        taskType = TaskType.DEADLINES;
    }

    @Override
    public LocalDate getDate() throws DukeException{
        return taskDate;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + taskDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
