import Task.*;

public class Deadline extends Task {
    protected boolean isDeadline;
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        isDeadline = false;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() +  "(by:" + by  + ")" ;
    }

}
