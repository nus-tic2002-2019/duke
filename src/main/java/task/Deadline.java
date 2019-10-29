package Task;

public class Deadline extends Task {

    String date;

    public Deadline(String Description, int value, String date){
        super(Description, value);
        this.date = date;
    }

    public Deadline(String Description, String date){
        super(Description, 0);
        this.date = date;
    }

    @Override
    public String toString(){
        return "[D][" + getStatusIcon() + "] " + this.description + " (by: " + this.date + ")";
    }
}
