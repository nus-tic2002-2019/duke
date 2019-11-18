package tic2002.task;

public abstract class Task {
    //Declare constant variables
    protected static final char CHAR_TODO = 'T';
    protected static final char CHAR_DEADLINE = 'D';
    protected static final char CHAR_EVENT = 'E';
    protected static final String CHAR_SEPARATOR_WRITE = "|";
    private static final String CHAR_FALSE = "0";
    private static final String CHAR_TRUE = "1";
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
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

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
    public void setDone() {
        isDone = true;
    }

    public void resetDone() {
        isDone = false;
    }

    public void setBoolDateTime() {
        isDateTimeFormat = true;
    }

    public void resetBoolDateTime() {
        isDateTimeFormat = false;
    }

    @Override
    public String toString() {
        return "[" + typeIdt + "][" + getStatusIcon() + "] " + taskDescription;
    }
}