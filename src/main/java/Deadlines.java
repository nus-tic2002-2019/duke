import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    protected LocalDate taskDate;
    protected LocalTime taskTime;
    protected boolean hasTime = false;

    public Deadlines(String description, LocalDate taskDate) {
        super(description);
        this.taskDate = taskDate;
        taskType = TaskType.DEADLINES;
    }

    public Deadlines(String description, LocalDate taskDate, LocalTime taskTime) {
        this(description, taskDate);
        this.taskTime = taskTime;
        this.hasTime = true;
        taskType = TaskType.DEADLINES;
    }

    public LocalDate getDate(){
        return taskDate;
    }
    public boolean isHasTime(){
        return hasTime;
    }
    public LocalTime getTime() throws DukeException{
        if(!hasTime){
            throw new DukeException("Please indicate time");
        }
        return taskTime;
    }

    public String getDateTimeString(){
        return hasTime
                ? taskDate + " " + taskTime
                : taskDate.toString();
    }

    public String getDateTimeStringFormat(){
        return hasTime
                ? taskDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + taskTime.format(DateTimeFormatter.ofPattern("hh:mm"))
                : taskDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateTimeStringFormat() + ")";
    }
}
