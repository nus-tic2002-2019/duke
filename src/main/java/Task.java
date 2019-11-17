public class Task {
    //Declare variables
    protected String taskDescription;
    protected boolean isDone;
    protected char typeIdt;

    //Constructor
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        isDone = false;
        typeIdt = 'A';
    }

    //Getter
    public String getTaskDescription() {
        return taskDescription;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public char getTypeIdentification() {
        return typeIdt;
    }

    //Setter
    public void setDone() {
        isDone = true;
    }

    public void resetDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + typeIdt + "][" + getStatusIcon() + "] " + taskDescription;
    }
}