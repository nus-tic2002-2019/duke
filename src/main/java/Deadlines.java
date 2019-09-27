public class Deadlines extends Task{
    protected String by;

    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getDescription() {
        return "[D]" + "[" + getStatusIcon() + "]" + super.getDescription() + " (by: " + by + ")";
    }

}
