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

    public void isDone() {
        this.done = true;
    }

    public String getStatusIcon() {
        return (done ? "\u2713" : "\u2718");
    }

    public String toString(){
        return "[" + getStatusIcon() + "] " + getTask();
    }

    public void print(){
        System.out.println(this.toString());
    }
}
