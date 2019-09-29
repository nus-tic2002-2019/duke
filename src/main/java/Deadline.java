public class Deadline extends Task {
    protected String eDescription;
    protected String eBy;

    //Declare variables for keywords
    protected String keyBy = "by";

    //Store keywords' number of characters
    protected int numBy = keyBy.length();

    //Constructor
    public Deadline(String description) {
        super(description.substring(0, description.indexOf('/') - 1) );
        typeIdt = 'D';

        eDescription = description.substring(0, description.indexOf('/') - 1);
        eBy = description.substring(description.indexOf('/') + 2 + numBy);
    }

    @Override
    public String toString() {
        return "[" + typeIdt + "][" + getStatusIcon() + "] " + eDescription + " (" + keyBy + ": " + eBy + ")";
    }
}