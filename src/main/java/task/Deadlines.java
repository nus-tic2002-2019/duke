package task;

public class Deadlines extends Task {
    String details;

    public Deadlines(String description, String details){
        super(description);
        this.details = "(by: "+ details + ")";
        this.type = 'D';
    }

    public String getDetails(){
        return details;
    }
}
