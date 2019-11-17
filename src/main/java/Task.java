public class Task {
    //Declare variables
    protected String taskDescription;
    protected boolean isDone;
    protected char typeIdt;

    //Declare constant variables
    protected static final String STRING_SEPARATOR_WRITE = "|";
    private static final String STRING_TRUE = "1";
    private static final String STRING_FALSE = "0";

    //Constructor
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        isDone = false;
        typeIdt = 'A';
    }

    //Getter
    public String getTaskDescription() {
        return taskDescription;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public char getTypeIdentification() {
        return typeIdt;
    }

    public String printToFile() {
        String output = typeIdt + STRING_SEPARATOR_WRITE;

        if (isDone) {
            output += STRING_TRUE;
        } else if (!isDone) {
            output += STRING_FALSE;
        }

        output += STRING_SEPARATOR_WRITE + taskDescription;

        return output;
    }

    //Setter
    public void setDone() {
        isDone = true;
    }

    public void resetDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + typeIdt + "][" + getStatusIcon() + "] " + taskDescription;
    }
}