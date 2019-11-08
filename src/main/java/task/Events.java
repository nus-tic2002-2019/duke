package task;


public class Events extends Task {
    String details;

    public Events(String description, String details) {
        super(description);
        this.type = 'E';
        this.details = "(at: " + details + ")";
    }

    /**
     * Returns a String of Location of the current event task
     * @return The location of the current event task in String
     */
    public String getDetails() {
        return details;
    }
}
