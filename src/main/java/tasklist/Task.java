package tasklist;

/**
 * Represent a task in a list.
 *
 */
public abstract class Task {
    protected String description, printTaskPriority;
    protected boolean isDone;
    protected Priority taskPriority;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription(){
        return description;
    }

    public void markAsDone(boolean status){
        this.isDone = status;
    }

    public void setTaskPriority(Priority taskPriority){
        this.taskPriority = taskPriority;
    }

    public String getTaskPriorityToString(){

        switch(taskPriority){
            case LOW:
                printTaskPriority = "L";
                break;
            case MEDIUM:
                printTaskPriority = "M";
                break;
            case HIGH:
                printTaskPriority = "H";

        }
        return printTaskPriority;
    }

    public Priority getTaskPriority(){
        return taskPriority;
    }

    public Priority getTaskPriorityFromString(String strTaskPriority){
        switch(strTaskPriority){
            case "L":
                taskPriority = Priority.LOW;
                break;
            case "M":
                taskPriority = Priority.MEDIUM;
                break;
            case "H":
                taskPriority = Priority.HIGH;
                break;
        }
        return taskPriority;
    }

    public abstract String saveToFile();


}
