public class Event extends Task {
    public Event(String desc) {
        super(desc);
    }

    public String getStatusIconAndDesc() {
        return "[E][" + super.getStatusIcon() + "] " + super.desc;
    }
}