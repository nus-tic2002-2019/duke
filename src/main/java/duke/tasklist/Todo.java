package duke.tasklist;

import java.time.LocalDate;

/**
 * Represent a Todo task in a list. It extends from the Task class and
 * A Todo object refers to a Todo task which has description and priority level.
 */
public class Todo extends Task {

    /**
     * The Todo constructor creates a Todo task object.
     * @param description is the description of the Todo task
     * @param taskPriority is the priority level for the Todo task
     */

    public Todo (String description, boolean isDone, Priority taskPriority){
        super(description);
        super.isDone = isDone;
        super.taskPriority = taskPriority;
    }
    /**
     * The getDescription method overrides the method in Task class and
     * returns the task in the following format to be represented on the screen
     * [T][task status][priority level]Todo task description
     */

    @Override
    public String getDescription() {
        return "[T]" + "[" + getStatusIcon() + "][" + super.getTaskPriorityToString() + "]" + super.getDescription();
    }

    /**
     * The saveToFile method overrides the method in Task class and
     * returns the task in the following format to be save to the file
     * T | Todo task status | priority level | Todo task description
     */
    @Override
    public String saveToFile(){
        int taskStatus = isDone ? 1:0;
        return "T | " + taskStatus + " | " + super.getTaskPriorityToString() + " | " + super.getDescription() + "\r";
    }
    /**
     * The getDate method overrides the method in Task class and
     * returns the date for the task. For Todo task, the date will
     * always be the local day TODAY.
     */
    public LocalDate getDate(){
        return LocalDate.now();
    }


}
