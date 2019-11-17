public class Task {
    //Declare variables
    protected String taskDescription;
    protected boolean isDone;
    protected char typeIdt;

    //Declare constant variables
    protected static final String CHAR_SEPARATOR_WRITE = "|";
    private static final String CHAR_FALSE = "0";
    private static final String CHAR_TRUE = "1";

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
        String output = typeIdt + CHAR_SEPARATOR_WRITE;

        if (isDone) {
            output += CHAR_TRUE;
        } else if (!isDone) {
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

    @Override
    public String toString() {
        return "[" + typeIdt + "][" + getStatusIcon() + "] " + taskDescription;
    }
}