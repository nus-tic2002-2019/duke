package task;

public class Deadline extends Task {
    public Deadline (String content) {
        super(content);
    }
    private String by;
    public Deadline (String content, String by){
        super(content);
        this.by = by;
    }
    public String getDate() {
        return by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
