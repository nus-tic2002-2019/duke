package tasklist;

import java.time.LocalDateTime;

public class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDateTime date;

    /**
     * Task Construction with task description
     *
     * @param description string of task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * To get status of the task object
     * @param isDone
     */
    public void markDone(boolean isDone)
    {
        this.isDone = isDone;
    }

    /**
     * to get the status icon of completed and incomplete task
     * @return String of check and cross symbol
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * To get the date time of object
     * @return LocalDateTime date and time of the object
     */
    public LocalDateTime getDateTime(){
        return this.date;
    }

    /**
     * Get the description of the object
     * @return String description of object
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * To geenerate task object in readable format and display it to the user.
     * @return String of readable format of the object
     */
    //@Override
    public String toString(){
        return "[" + this.getStatusIcon() + "]" + this.getDescription();
    }

    /**
     *To generate string for which to write todo  object into file over a specific format in | separated
     * @return String format of objects to be written to file
     */
    public String writeToFile()
    {
        Integer intIsDone = isDone ? 1 : 0;
        return " | " + intIsDone + " | ";

    }
}
