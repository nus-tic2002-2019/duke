public class Task {
    protected String taskName;
    protected boolean taskDone;

    public Task (String taskName, boolean taskDone)
    {
        this.taskName = taskName;
        this.taskDone = taskDone;
    }

    public String getTaskName()
    {
        return taskName;
    }

    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }

    public boolean getTaskDone()
    {
        return taskDone;
    }

    public void setTaskDone(boolean taskDone)
    {
        this.taskDone = taskDone;
    }
}
