public class Deadline extends Task{
    protected String by;
    public Deadline (String taskName, boolean taskDone, String by)
        {
            super(taskName, taskDone); // calls the parent constructor
            this.by = by;
        }

    public String toString()
    {
        return "[D]" + super.toString() + " (by : "+ by + ")";
    }
    }

