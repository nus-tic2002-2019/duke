public class Task {
    private String description;
    protected boolean isDone;

    public Task(String description) {
        this.setDescription(description);
        this.isDone = false;
    }
    public Task(String description,boolean isDone) {
        this.setDescription(description);
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return "[" +(isDone ? "\u2713" : "\u2718")+"]" ; //return tick or X symbols
    }

    public void setStatus(String status) {
        this.isDone = (status.equals("1")) ? true : false;
    }

    public String markAsDone() {
        this.isDone = true;
        return (getStatusIcon()); //return tick or X symbols
    }

    public char getTaskType() {
        return 'n';
    }

    @Override 
    public String toString(){
        return getStatusIcon() + " " + this.getDescription();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}