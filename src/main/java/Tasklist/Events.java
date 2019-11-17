package Tasklist;

public class Events extends Task {
    protected boolean isDone;
    protected String at;

    public Events(String description, String at)
    {
        super(description);
        isDone=false;
        this.at = at;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[E] [" + super.getStatusIcon() +"]" + super.getDescription() + "(at: " + at + ")" ;
    }

    @Override
    public String writeToFile()
    {
        return "E" + super.writeToFile() + super.getDescription() + " | " + at;
    }
}
