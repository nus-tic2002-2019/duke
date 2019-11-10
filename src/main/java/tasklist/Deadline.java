package tasklist;

public class Deadline extends Task {

    public String time= "Time not specified";
    public String Type = "Deadline";

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    public Deadline(String description, Boolean status) {
        super(description,status);

    }

    public Deadline(String description,Boolean status, String time) {
        super(description,status);
        this.time = time;
    }


    @Override
    public String toString(){
        return("[D]" + super.toString() + " (by: " + this.time+ ")");
    }

    @Override
    public String saveFormat(){
        return ("D " + super.saveFormat() +  " | " + this.time);
    }



}
