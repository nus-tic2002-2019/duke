import java.util.Date;

public class Task {
//attributes
    public String description;
    public boolean done;
// constructor
    public Task() {
    }

    public void setDescription(String description){
        this.description = description;
    }
    public void printDescription(){
        System.out.println(this.description);
    }
}
