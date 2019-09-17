public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        Duke.current += 1;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setIsDone(){
        this.isDone = true;
        System.out.println("Nice!! I've marked this task as done: \n" + "[" + this.getStatusIcon() + "] " + this.description);
        System.out.printf("\n");
    }

}
