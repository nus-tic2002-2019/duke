package tic2002.task;

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
    protected boolean isDateTimeFormat = false;

    //Constructor
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        isDone = false;
    }

    //Getter
    /**
     * Returns appropriate symbol of Task status.
     *
     * @return String of appropriate symbol.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Combine and return String for writing into file, for Task class.
     *
     * @return String of combined output.
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

        output += CHAR_SEPARATOR_WRITE + taskDescription;

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

    @Override
    public String toString() {
        return "[" + typeIdt + "][" + getStatusIcon() + "] " + taskDescription;
    }
}