package Tasks;
import Tasks.myMethods;
public class Events extends Task{
    public Events(String description){
        super(description);
        isDone = false;
    }

    @Override
    public String getDescription(){
        return getStatus() + getStatusIcon() + " " + new myMethods().parseEvents(description) + new myMethods().parseSlash(description);
    }

    @Override
    public String getStatus() {
        super.getStatus();
        //System.out.println("[T]");
        return "[E]";
    }

    @Override
    public String toString() {
        return "[E]" +  this.getStatusIcon() + new myMethods().parseEvents(description) + new myMethods().parseSlash(description);
    }
}
