/*
Task.java
Define task.
*/

package Duke.tasks;

import java.io.Serializable;

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /*
    constructor of task object.
    @param description task description
    */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /*
    get task status by return tick or x symbols.
    @return tick or x symbols
    */
    public String getStatusIcon() {

        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /*
    get task description
    @return task description
    */
    public String getDescription()
    {
        return description;
    }

    /*
    set task status if it is done.
    */
    public void markAsDone()
    {
        isDone =  true;
    }
}