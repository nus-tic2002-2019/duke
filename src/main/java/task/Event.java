package task;

public class Event extends Task {
    protected String date;

    public Event(String desc, String date) {
        super(desc, "E");
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public String getStatusIconAndDesc() {
        return "[" + this.type + "][" + this.getStatusIcon() + "] " + this.desc + " (at: " + this.date + ")";
    }
}