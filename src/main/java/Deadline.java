public class Deadline extends Task {
    //Declare variables
    protected String deadlineDescription;
    protected String deadlineByString;

    //Declare constant variables
    protected final String STRING_BY = "by";
    protected final char STRING_SEPARATOR = '/';

    //Store keywords' number of characters
    protected int byStrLen = STRING_BY.length();

    //Constructor
    public Deadline(String taskDescription) {
        super(taskDescription.substring(0, taskDescription.indexOf('/') - 1) );
        typeIdt = 'D';

        deadlineDescription = (taskDescription.substring(0, taskDescription.indexOf(STRING_SEPARATOR) - 1) );
        deadlineByString = taskDescription.substring(taskDescription.indexOf(STRING_SEPARATOR) + 2 + byStrLen);
    }

    public Deadline(String taskDescription, String taskTime) {
        super(taskDescription);
        typeIdt = 'D';

        deadlineDescription = (taskDescription);
        deadlineByString = taskTime;
    }

    //Getter
    public String printToFile() {
        return(super.printToFile() + CHAR_SEPARATOR_WRITE + deadlineByString);
    }

    @Override
    public String toString() {
        return "[" + typeIdt + "][" + getStatusIcon() + "] " + deadlineDescription + " (" + STRING_BY + ": " + deadlineByString + ")";
    }
}