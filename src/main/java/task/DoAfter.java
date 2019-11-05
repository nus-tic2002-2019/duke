package task;

public class DoAfter extends Deadlines {

    public DoAfter(String description, String details, boolean readFromFile) {
        super(description, details, readFromFile);
        this.type = 'A';
    }

    public String getDetails() {
        return "after " + this.stringDateTime;
    }
}
