package tic2002.task;

import tic2002.enumerations.Priority;

/**
 * Represents Task class.
 */
public abstract class Task {
    //Declare constant variables
    protected static final char CHAR_TODO = 'T';
    protected static final char CHAR_DEADLINE = 'D';
    protected static final char CHAR_EVENT = 'E';
    protected static final String CHAR_SEPARATOR_WRITE = "|";
    protected static final String CHAR_FALSE = "0";
    protected static final String CHAR_TRUE = "1";
    protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd HHmm";
    protected static final String DATE_TIME_FORMAT_OUTPUT = "d MMMM yyyy, h:mm a";

    //Declare variables
    protected String taskDescription;
    protected boolean isDone;
    protected char typeIdt;
    protected boolean isDateTimeFormat;
    protected Priority taskPriority;
    protected String preOutput;
    protected String priorityOutput;

    //Constructor
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        isDone = false;
        isDateTimeFormat = false;
        taskPriority = Priority.NONE;
    }

    //Getter

    /**
     * Returns task description.
     *
     * @return String
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Returns appropriate symbol of Task status.
     *
     * @return String
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Combine and return String for writing into file, for Task class.
     *
     * @return String
     */
    public String printToFile() {
        String output = typeIdt + CHAR_SEPARATOR_WRITE;

        if (isDone) {
            output += CHAR_TRUE;
        } else {
            output += CHAR_FALSE;
        }

        output += CHAR_SEPARATOR_WRITE;

        if (isDateTimeFormat) {
            output += CHAR_TRUE;
        } else {
            output += CHAR_FALSE;
        }

        output += CHAR_SEPARATOR_WRITE + taskPriority + CHAR_SEPARATOR_WRITE + taskDescription;

        return output;
    }

    //Setter
    /**
     * Sets isDone boolean of Task class to true.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Sets isDone boolean of Task class to false.
     */
    public void resetDone() {
        isDone = false;
    }

    /**
     * Sets isDateTimeFormat boolean of Task class to true.
     */
    public void setBoolDateTime() {
        isDateTimeFormat = true;
    }

    /**
     * Sets isDateTimeFormat boolean of Task class to false.
     */
    public void resetBoolDateTime() {
        isDateTimeFormat = false;
    }

    /**
     * Set taskPriority according to user input.
     *
     * @param currentPriority
     */
    public void setTaskPriority(Priority currentPriority) {
        taskPriority = currentPriority;
    }

    /** Generate pre-output for consistent use of this and its child class */
    protected void setPreOutput() {
        preOutput = "[" + typeIdt + "][" + getStatusIcon() + "] " + taskDescription;
        priorityOutput = "<Priority: " + taskPriority + ">";
    }

    @Override
    public String toString() {
        setPreOutput();

        if (taskPriority == Priority.NONE) {
            return preOutput;
        } else {
            return preOutput + " " + priorityOutput;
        }
    }
}