//level 7.more oop
/**
 *  Todo command for storing Data "Description"
 */

package task;


public class Todo extends Task {

    public Todo(String description) {
        super(description);
        //isDone = false;
    }

    @Override
    public String toString() {

        return "[T]" + super.toString();
      //return "[T][" + super.getStatusIcon() + "] " + super.getDescription();
    }

}

/**
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
**/