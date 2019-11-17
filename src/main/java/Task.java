import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * To set the Task to completed status upon Users input
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Return the icon based on done status: Done or Not Done
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Return the Task format to String format
     */
    public String toString () {
        return "[" + this.getStatusIcon() + "] " +  this.description ;
    }

    public void print_Task() {
        System.out.println("[D]" + super.toString() + ")");
    }
}
