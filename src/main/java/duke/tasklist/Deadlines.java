package duke.tasklist;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Represent a deadline task in a list. It extends from the Task class and
 * a Deadline object refers to a deadline task which has description, date and priority level.
 */

public class Deadlines extends Task {
    protected LocalDate Date;
    private DateTimeFormatter displayDateFormat = DateTimeFormatter.ofPattern("MMM d yyyy");
    private DateTimeFormatter saveDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /**
     * The Deadline constructor creates a deadline object.
     * @param description is the description of the Deadline
     * @param Date is the deadline Date
     * @param isDone is the Deadline task status
     * @param taskPriority is the priority level for the task
     */
    public Deadlines(String description, LocalDate Date, boolean isDone, Priority taskPriority){

        super(description);
        this.Date = Date;
        super.isDone = isDone;
        super.taskPriority = taskPriority;
    }
    /**
     * The getDescription method overrides the method in Task class and
     * returns the task in the following format to be represented on the screen
     * [D][task status][priority level]Deadline description (Deadline Date)
     */
    @Override
    public String getDescription() {
        return "[D]" + "[" + getStatusIcon() + "][" + super.getTaskPriorityToString() + "]" + super.getDescription() + " (by:" + Date.format(displayDateFormat) + ")";
    }
    /**
     * The saveToFile method overrides the method in Task class and
     * returns the task in the following format to be save to the file
     * D | task status | priority level | task description | Deadline Date
     */
    @Override
    public String saveToFile(){
        int taskStatus = isDone ? 1:0;
        return "D | " + taskStatus + " | " + super.getTaskPriorityToString() + " | " + super.getDescription() + " | " + Date.format(saveDateFormat) + "\r";
    }



}
