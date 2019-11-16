package task;
/**
 * Represents the todo class that initiated by task.
 * */
public class ToDo extends Task {
    /**
     * Creation of checker variable.
     */
    protected boolean isDone;

    public ToDo(){
        super();
    }

    public ToDo(String s){
        super(s);
    }
    /**
     * Checks description input and boolean status,
     * then returns the respective description and boolean status.
     * */
    public ToDo(String s, boolean isDone){
        super(s);
        this.isDone=isDone;
    }
    /**
     * calls the function print to print the icon.
     * */
    public String list(){
        return "[T]" + print();
    }
    /**
     * Prints icon according to respective action.
     * */
    public String print(){
        if(isDone) {
            return "[\u2713] "+super.print();
        }else{
            return "[\u2718] "+super.print();
        }
    }
    public void setDone(boolean isDone){
        this.isDone=isDone;
    }
    /**
     * Retrieves boolean from parent class task.
     * */
    public boolean getDone(){
        return this.isDone;
    }
    /**
     * Retrieves string of text from parent class task.
     * */
    public String getText(){
        return super.getText();
    }

}