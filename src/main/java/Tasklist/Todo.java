package Tasklist;

public class Todo extends Task
{
    protected boolean isDone;

    public Todo(String description)
    {
        super(description);
        isDone=false;
    }
    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[T] [" + super.getStatusIcon() +"]" + super.getDescription();
    }

    @Override
    public String writeToFile()
    {
        //return "D | " + isDone + " | " + this.getDescription();
        return "T" + super.writeToFile() + super.getDescription() ;
    }
}
