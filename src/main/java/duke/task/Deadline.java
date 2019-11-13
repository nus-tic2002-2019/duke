package duke.task;

public class Deadline extends Task {
    protected String date;

    public Deadline(String desc, String date) {
        super(desc, "D");
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public String getStatusIconAndDesc() {
        return "[" + this.type + "][" + this.getStatusIcon() + "] " + this.desc + " (by: " + this.date + ")";
    }
}