/* no more in used

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone(boolean isDone) {
        this.isDone = isDone;
    }

//    public boolean isDone() {
//        return isDone;
//    }


    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols

    }


    public String getDescription() {
        return description;
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }



}

*/ 