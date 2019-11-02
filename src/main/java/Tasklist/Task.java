package Tasklist;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone(boolean isDone)
    {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription()
    {
        return description;
    }

    //@Override
    public String toString(){
        return "[" + this.getStatusIcon() + "]" + this.getDescription();
    }

    public String writeToFile()
    {
        //return "[" + this.getStatusIcon() + "]" + this.getDescription();
        Integer intIsDone = isDone ? 1 : 0;
        return " | " + intIsDone + " | ";

        //return " | " + String.valueOf(int intIsDone = isDone ? 1 : 0) + " | ";
    }
}
