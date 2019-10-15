package duke.task;

public abstract class Task {
    protected String task;
    protected Boolean done;

    //Constructor
    public Task(String input)
    {
        input = input.trim();
        this.task = input;
        this.done = false;
    }

    /**
     * Method to return and print the description of the task with the completion status.
     *
     * @return task printed in format of [status] task description
     */
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
