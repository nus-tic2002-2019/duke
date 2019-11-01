package Tasks;

public class Deadlines extends Task {

    protected String by;

    public Deadlines(String description, String by) {
        super(description);
        isDone = false;
        this.by = by;

    }

    @Override
    public String getStatus() {
        super.getStatus();
        //System.out.println("[T]");
        return "[D]";
    }

    @Override
    public String toString() {
        return "[D]" + this.getStatusIcon() + this.description + " (by: " + by + ")";
    }
}
