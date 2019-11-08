package task;

public class DoAfter extends Deadlines {

    public DoAfter(String description, String details, boolean readFromFile) {
        super(description, details, readFromFile);
        this.type = 'A';
    }

    /**
     * Get date & time of the current DoAfter task
     *
     * @return The current Date and Time in String
     */
    public String getDetails() {
        return "after " + this.stringDateTime;
    }
}
