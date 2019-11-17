public class Deadline extends Task {
    //Declare variables
    protected String deadlineByString;

    //Declare constant variables
    protected final String STRING_BY = "by";
    protected final char CHAR_SEPARATOR = '/';

    //Store keywords' number of characters
    protected int byStrLen = STRING_BY.length();

    //Constructor
    public Deadline(String taskDescription) {
        super(taskDescription.substring(0, taskDescription.indexOf('/') - 1) );
        typeIdt = CHAR_DEADLINE;

        deadlineByString = taskDescription.substring(taskDescription.indexOf(CHAR_SEPARATOR) + 2 + byStrLen);
    }

    public Deadline(String taskDescription, String taskTime) {
        super(taskDescription);
        typeIdt = CHAR_DEADLINE;

        deadlineByString = taskTime;
    }

    //Getter
    public String printToFile() {
        return(super.printToFile() + CHAR_SEPARATOR_WRITE + deadlineByString);
    }

    @Override
    public String toString() {
        return "[" + typeIdt + "][" + getStatusIcon() + "] " + taskDescription + " (" + STRING_BY + ": " + deadlineByString + ")";
    }
}