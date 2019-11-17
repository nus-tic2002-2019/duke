package duke.tasklist;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
/**
 * Represent an event task in a list. It extends from the Task class and
 * an Event object refers to an Event task which has description,date,start time, end time and the priority level.
 */

public class Event extends Task {
    private LocalDate Date;
    private LocalTime startTime, endTime;
    private DateTimeFormatter displayDateFormat = DateTimeFormatter.ofPattern("MMM d yyyy");
    private DateTimeFormatter displayTimeFormat = DateTimeFormatter.ofPattern("HH:mm");
    private DateTimeFormatter saveDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter saveTimeFormat = DateTimeFormatter.ofPattern("HH:mm");
    /**
     * The event constructor creates an event object.
     * @param description is the description of the Event task
     * @param Date is the Event Date
     * @param startTime is the Event Start Time
     * @param endTime is the Event End Time
     * @param isDone is the Event task status
     * @param taskPriority is the priority level for the Event task
     */
    public Event(String description, LocalDate Date, LocalTime startTime, LocalTime endTime, boolean isDone, Priority taskPriority){
        super(description);
        this.Date = Date;
        this.startTime = startTime;
        this.endTime = endTime;
        super.isDone = isDone;
        super.taskPriority = taskPriority;
    }
    /**
     * The getDescription method overrides the method in Task class and
     * returns the task in the following format to be represented on the screen
     * [E][Event task status][priority level]Event task description (Event Date Start Time - End Time)
     */

    @Override
    public String getDescription() {
        return "[E]" + "[" + getStatusIcon() + "][" + super.getTaskPriorityToString() + "]" + super.getDescription() + " (at:" + Date.format(displayDateFormat) + " " + startTime.format(displayTimeFormat) + " - " + endTime.format(displayTimeFormat) + ")";
    }
    /**
     * The saveToFile method overrides the method in Task class and
     * returns the task in the following format to be save to the file
     * E | Event task status | priority level | Event task description | Event Date Start Time - End Time
     */
    @Override
    public String saveToFile(){
        int taskStatus = isDone ? 1:0;
        return "E | " + taskStatus + " | " + super.getTaskPriorityToString() + " | " + super.getDescription() + " | " + Date.format(saveDateFormat) + " " + startTime.format(saveTimeFormat) + " - " + endTime.format(saveTimeFormat) + "\r";
    }

    public LocalDate getDate() {
        return Date;
    }
}
