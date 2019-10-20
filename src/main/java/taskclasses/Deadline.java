package taskclasses;

public class Deadline extends Task {
    public Deadline(String description, String date, String time, String datetime){
        super(description, "D");
        Time = time;
        Date = date;
        DateTime = datetime;
    }
}
