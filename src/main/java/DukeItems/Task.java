package DukeItems;

public abstract class Task {
    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {

        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getDescription(){
        return description;
    }


    public abstract String getTaskType();
    public abstract String getDue();
    public abstract void setTags(String tag);
    public abstract boolean verifyTag(String tag);


    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}