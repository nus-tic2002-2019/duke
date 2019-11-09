//level 7
package task;

public class Todo extends Task {
    protected boolean isDone;

    public Todo(String description) {
        super(description);
        isDone = false;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[T] [" + super.getStatusIcon() + "] " + super.getDescription();
    }

    @Override
    public String SaveFile(){
        return "T" + super.SaveFile() + super.getDescription();
    }

}
