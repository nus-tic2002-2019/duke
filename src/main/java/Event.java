public class Event extends Task {
    protected String eDescription;
    protected String eAt;

    //Declare variables for keywords
    protected String keyAt = "at";

    //Store keywords' number of characters
    protected int numAt = keyAt.length();

    //Constructor
    public Event(String description) {
        super(description.substring(0, description.indexOf('/') - 1) );
        typeIdt = 'E';

        eDescription = description.substring(0, description.indexOf('/') - 1);
        eAt = description.substring(description.indexOf('/') + 2 + numAt);
    }

    @Override
    public String toString() {
        return "[" + typeIdt + "][" + getStatusIcon() + "] " + eDescription + " (" + keyAt + ": " + eAt + ")";
    }
}