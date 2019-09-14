public class Task {
    private String task;
    boolean done;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public String getTask(){
        return task;
    }

    public boolean getDone(){
        return done;
    }

    public void taskDone() {
        this.done = true;
    }

    public String getStatusIcon() {
        return (done ? "\u2713" : "\u2718");
    }

    public void print(){
        System.out.println("[" + getStatusIcon() + "] " + getTask());
    }
}
