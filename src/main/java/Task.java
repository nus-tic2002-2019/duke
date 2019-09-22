public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public Character getTaskType() {
        char taskType = description.charAt(0);
        return Character.toUpperCase(taskType);
    }

    public void markAsDone(){
        isDone = true;
        getStatusIcon();
    }
}
