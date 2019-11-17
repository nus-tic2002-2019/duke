package Tasks;

import Exception.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class which the user want to store with date and time
 */
public class Deadlines extends Task {
    protected LocalDate taskDate;
    protected LocalTime taskTime;
    protected boolean hasTime = false;

    /**
     * Constructs the Deadlines class without a time
     * @param description the description the user input
     * @param taskDate the date the user input
     */
    public Deadlines(String description, LocalDate taskDate) {
        super(description);
        this.taskDate = taskDate;
        taskType = TaskType.DEADLINES;
    }

    /**
     * Constructs the Event class with a time
     * @param description the description the user input
     * @param taskDate the date the user input
     * @param taskTime the time the user input
     */
    public Deadlines(String description, LocalDate taskDate, LocalTime taskTime) {
        this(description, taskDate);
        this.taskTime = taskTime;
        this.hasTime = true;
        taskType = TaskType.DEADLINES;
    }

    /**
     * return the date that was stored
     * @return the date that was stored
     */
    public LocalDate getDate(){
        return taskDate;
    }

    /**
     * return true or false if the class has time
     * @return true or false if the class has time
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
     * return the date and time that was stored in string
     * @return the date and time that was stored in string
     */
    public String getDateTimeString(){
        return hasTime
                ? taskDate + " " + taskTime
                : taskDate.toString();
    }

    /**
     * return the date and time formatted that was stored in string
     * @return the date and time formatted that was stored in string
     */
    public String getDateTimeStringFormat(){
        return hasTime
                ? taskDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + taskTime.format(DateTimeFormatter.ofPattern("hh:mm a"))
                : taskDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Return the task type with the task that the user input and whether it is done or not
     * @return the task in String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateTimeStringFormat() + ")";
    }
}
