package Tasks;

import Exception.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 */
public class Deadlines extends Task {
    protected LocalDate taskDate;
    protected LocalTime taskTime;
    protected boolean hasTime = false;

    /**
     *
     * @param description
     * @param taskDate
     */
    public Deadlines(String description, LocalDate taskDate) {
        super(description);
        this.taskDate = taskDate;
        taskType = TaskType.DEADLINES;
    }

    /**
     *
     * @param description
     * @param taskDate
     * @param taskTime
     */
    public Deadlines(String description, LocalDate taskDate, LocalTime taskTime) {
        this(description, taskDate);
        this.taskTime = taskTime;
        this.hasTime = true;
        taskType = TaskType.DEADLINES;
    }

    /**
     *
     * @return
     */
    public LocalDate getDate(){
        return taskDate;
    }

    /**
     *
     * @return
     */
    public boolean isHasTime(){
        return hasTime;
    }

    /**
     *
     * @return
     * @throws DukeException
     */
    public LocalTime getTime() throws DukeException {
        if(!hasTime){
            throw new DukeException("Please indicate time");
        }
        return taskTime;
    }

    /**
     *
     * @return
     */
    public String getDateTimeString(){
        return hasTime
                ? taskDate + " " + taskTime
                : taskDate.toString();
    }

    /**
     *
     * @return
     */
    public String getDateTimeStringFormat(){
        return hasTime
                ? taskDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + taskTime.format(DateTimeFormatter.ofPattern("hh:mm a"))
                : taskDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateTimeStringFormat() + ")";
    }
}
