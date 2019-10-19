public class Task {
    protected String description;
    protected boolean isDone;
    //protected boolean tobedo;

    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718");
    }

    //public String setDone() {
      //  this.isDone = true;
        //return "Nice! I've marked this task as done: " + "[\u2713]" + this.getStatusIcon();
    //}
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + this.description;
    }
    public String setDone() {
        this.isDone = true;
        return "Nice! I've marked this task as done: " + "[\u2713]" + this.description;
    }
    public String setDelete() {
        return "Noted. I've removed this task:\n\t " + toString();
    }
}
