//level 7.more oop

package task;

public class Events extends Task {
    private boolean isDone;
    private String description;
    private String at;

    public Events(String description, String at) {
        super(description);
        isDone = false;
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }

}



/**
public class Events extends Task {
    private boolean isDone;
    private String at;

    public Events(String description, String at){
        super(description);
        isDone = false;
        this.at = at;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString(){
        return "[E] [" + super.getStatusIcon() + "] " + super.getDescription() + "(at: " + at + ")";
    }

    @Override
    public String SaveFile(){

        return "E" + super.SaveFile() + super.getDescription() + " | " + at;
    }

}

**/