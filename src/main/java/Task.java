public class Task {
    protected String task;
    protected Boolean done;

    //Constructor
    public Task(String input)
    {
        this.task = input;
        this.done = false;
    }

    //Accessor
    public String printTask()
    {
        return "[" + this.getIcon() + "] " + this.task;
    }

    public Boolean isDone()
    {
        return this.done;
    }

    public String getIcon() {
        return (done ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    //Modifier
    public void markDone()
    {
        this.done = true;
    }

}
