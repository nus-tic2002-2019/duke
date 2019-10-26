import java.util.Date;

public class Task {
//attributes
    protected String description;
    protected boolean done = false;


// constructor

    public Task(String description) {
        this.description = description;
    }
    public Task(){}

    public void setDescription(String description){
        this.description = description;
    }
    public void print(){
        String doneMessage = this.getDoneMessage();
        System.out.print(doneMessage);
        System.out.println(this.description);
    }

    public String getDoneMessage() {
        if (this.done == false){
            return "(Not done)";
        }
        else {return "(Done)";}
    }

    public void printDoneMessage() {
        if (this.done == false){
            System.out.print ("(Not done)");
        }
        else {System.out.print("(Done)");}
    }

    public String getSaveFormat(){
        return "";
    }
}
