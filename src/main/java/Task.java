public abstract class Task {
    protected String storeWords;
    protected boolean done;


    public Task(String thingsToDo){
        this.storeWords = thingsToDo;
        this.done = false;
    }

    public String getTask(){
        return storeWords;
    }
    public boolean isDone(){
        return done;
    }
    public void edit_done(boolean is_it_done){
        done = is_it_done;
    }
    public void edit_task(String thingsToDo){
        storeWords = thingsToDo;
    }
    public String getStatusIcon() {
        return (done ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    public String toString() {
        return " [" + this.getStatusIcon() + "] " + this.getTask();
    }


}