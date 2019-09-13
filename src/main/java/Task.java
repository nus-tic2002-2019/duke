import java.util.Date;

public class Task {
//attributes
    protected String description;
    protected boolean done = false;
    protected String starttime;
    protected String endtime;

// constructor
    public Task() {
    }

    public void setDescription(String description){
        this.description = description;
    }
    public void print(){
        System.out.println(this.description);
    }

    public String getDoneMessage() {
        if (this.done == false){
            return "(Not done)";
        }
        else {return "(Done)";}
    }
}
