public class Event extends Task {
    //Declare constant variables
    protected final String STRING_AT = "at";
    protected final char CHAR_SEPARATOR = '/';

    //Store keywords' number of characters
    protected int atStrLen = STRING_AT.length();

    //Declare variables
    protected String eventAtString;

    //Constructor
    public Event(String taskDescription) {
        super(taskDescription.substring(0, taskDescription.indexOf('/') - 1) );
        typeIdt = CHAR_EVENT;

        eventAtString = taskDescription.substring(taskDescription.indexOf(CHAR_SEPARATOR) + 2 + atStrLen);
    }

    public Event(String taskDescription, String taskTime) {
        super(taskDescription);
        typeIdt = CHAR_EVENT;

        eventAtString = taskTime;
    }

    //Getter
    public String printToFile() {
        return(super.printToFile() + CHAR_SEPARATOR_WRITE + eventAtString);
    }

    @Override
    public String toString() {
        return "[" + typeIdt + "][" + getStatusIcon() + "] " + taskDescription + " (" + STRING_AT + ": " + eventAtString + ")";
    }
}