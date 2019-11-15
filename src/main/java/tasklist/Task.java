package tasklist;

import java.text.ParseException;
import java.util.Date;

public class Task{
    public String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean status) {
        this.description = description;
        this.isDone = status;
    }


    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone(){
        isDone = true;
        getStatusIcon();
    }

    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + description);
    }

    public String saveFormat(){
        if (this.isDone == Boolean.parseBoolean("true")){
            return ("| 1 | " + this.description);
        }
        else {
            return ("| 0 | " + this.description);
        }
    }

    public boolean findDate(Date dateSearch, String taskType) throws ParseException {
        return false;
    }

    public boolean findFromDateRange(Date dateSearch, String taskType) throws ParseException {
        return false;
    }

    public boolean findBetweenDateRange(Date fromDate, Date endDate, String taskType) throws ParseException {
        return false;
    }

    public boolean taskType(String taskType){
        if (taskType.equals("tasks")){
            return true;
        }
        return false;
    }


}
