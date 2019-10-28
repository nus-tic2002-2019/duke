package Task;

public class Task {

    protected String description;
    protected boolean isDone = false;

    public Task(String description, int value) {
        this.description = description;
        this.isDone = (value == 1 ? true : false);
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public void setIsDone(){
        this.isDone = true;
        System.out.println("Nice!! I've marked this task as done: \n" + "[" + this.getStatusIcon() + "] " + this.description);
        System.out.printf("\n");
    }

}
