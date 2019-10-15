package duke.task;

import java.util.ArrayList;

//WIP
public class TaskList {
    protected ArrayList<Task> tasks;

    public enum act{
        DELETE
    }

    public TaskList()
    {
        this.tasks = new ArrayList<>();
    }
}
