public class Task {
    protected String desc;
    protected boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
        Duke.print("New task added: \n\t" + this.getStatusIconAndDesc() + "\n" + (Duke.toDoListSize()+1) + " tasks in your list :)");
    }

    public String getStatusIcon() {
        if(isDone)
            return "\u2713";
        else
            return "\u2718";
    }

    public String getDesc() {
        return this.desc;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getStatusIconAndDesc() {
        return "[-][" + this.getStatusIcon() + "] " + this.desc;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void setDone(boolean status) {
        this.isDone = status;
    }



}