package Tasks;

import Exception.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The event class which the user want to store
 */
public class Events extends Task {
    protected LocalDate taskDate;
    protected LocalTime taskTime;
    protected boolean hasTime = false;

    /**
     * Constructs the Event class without a time
     * @param description the description the user input
     * @param taskDate the date that the user input
     */
    public Events(String description, LocalDate taskDate) {
        super(description);
        this.taskDate = taskDate;
        taskType = TaskType.EVENTS;
    }

    /**
     * Constructs the Event with a time
     * @param description the description the user input
     * @param taskDate the date the user input
     * @param taskTime the time the user input
     */
    public Events(String description, LocalDate taskDate, LocalTime taskTime) {
        this(description, taskDate);
        this.taskTime = taskTime;
        this.hasTime = true;
        taskType = TaskType.EVENTS;
    }

    /**
     * return the date that was stored
     * @return the date that was stored
     */
    public LocalDate getDate(){
        return taskDate;
    }

    /**
     * return if the class has a time or not
     * @return hasTime
     */
    public boolean isHasTime(){
        return hasTime;
    }

    /**
     * return the time of the event
     * @return the task time
     * @throws DukeException the expected error
     */
    public LocalTime getTime() throws DukeException {
        if(!hasTime){
            throw new DukeException("Please indicate time");
        }
        return taskTime;
    }

    /**
     * return the date and time of the task in String
     * @return the date and time in String
     */
    public String getDateTimeString(){
        return hasTime
                ? taskDate + " " + taskTime
                : taskDate.toString();
    }

    /**
     * return the date and time formatted in String
     * @return the formatted date and time in String
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
        return "[E]" + super.toString() + " (at: " + getDateTimeStringFormat() + ")";
    }
}
