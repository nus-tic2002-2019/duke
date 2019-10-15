package taskclasses;

public class Deadline extends Task {
    public Deadline(String description, String by){
        super(description, "D");
        Time = by;
    }
}
