package task;
/**
 * Represents the parent class that used by deadline, event and todo.
 * */
abstract public class Task {
    /**
     * Creation of text variable.
     * */
    protected String text;

    public Task() {

    }
    /**
     * Creation of text variable.
     * */
    public Task(String s){
        text=s;
    }
    /**
     * Return user input of text.
     * */
    public String list(){
        return text;
    }
    /**
     * Return user input of text.
     * */
    public String print(){
        return text;
    }
    /**
     * utilized by child classes.
     * */
    abstract public void setDone(boolean isDone);
    /**
     * utilized by child classes.
     * */
    abstract public boolean getDone();
    /**
     * Returns user input of text.
     * */
    public String getText(){
        return text;
    }

}