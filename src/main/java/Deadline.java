public class Deadline extends Task {
    public Deadline(String desc) {
        super(desc);
    }

    public String getStatusIconAndDesc() {
        return "[D][" + super.getStatusIcon() + "] " + super.desc;
    }
}