public class Event extends Task {
    protected String eventDescription;
    protected String eventAtString;

    //Declare constant variables
    protected final String STRING_AT = "at";
    protected final char STRING_SEPARATOR = '/';

    //Store keywords' number of characters
    protected int atStrLen = STRING_AT.length();

    //Constructor
    public Event(String taskDescription) {
        super(taskDescription.substring(0, taskDescription.indexOf('/') - 1) );
        typeIdt = 'E';

        eventDescription = taskDescription.substring(0, taskDescription.indexOf(STRING_SEPARATOR) - 1);
        eventAtString = taskDescription.substring(taskDescription.indexOf(STRING_SEPARATOR) + 2 + atStrLen);
    }

    //Getter
    public String printToFile() {
        return(super.printToFile() + STRING_SEPARATOR_WRITE + eventAtString);
    }

    @Override
    public String toString() {
        return "[" + typeIdt + "][" + getStatusIcon() + "] " + eventDescription + " (" + STRING_AT + ": " + eventAtString + ")";
    }
}