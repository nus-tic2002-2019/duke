//level 7.more oop

package task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols

    }

    public String getDescription() {
        return description;
    }

    public boolean taskDone() {
        return isDone = true;
    }

    @Override
    public String toString() {
        return "[" + (isDone? "\u2713" : "\u2718") + "] " + description;
    }


}

/**
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
 ////        return isDone;
 ////    }


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

    //need to save
    public String SaveFile(){
        Integer intIsDone = isDone ? 1:0;
        return " | " + intIsDone + " | ";
    }

}
**/