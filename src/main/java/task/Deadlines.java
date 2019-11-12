//level 7.more oop

package task;

public class Deadlines extends Task {
    private boolean isDone;
    private String description;
    private String by;

    public Deadlines(String description, String by) {
        super(description);
        isDone = false;
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(at: " + by + ")";
    }

}

/**
public class Deadlines extends Task {
    protected boolean isDone;
    protected String by;
    //private String by;

    public Deadlines(String description, String by) {
        super(description);
        isDone = false;
        this.by = by;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString(){
        return "[D] [" + super.getStatusIcon() + "] " + super.getDescription() + "(by: " + by + ")";
        //return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String SaveFile(){
        return "E" + super.SaveFile() + super.getDescription() + " | " + by;
    }

}
**/