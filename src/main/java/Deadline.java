public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public Deadline(String status,String description, String by) {
        super(description);
        this.by = by;
        this.setStatus(status);
    }

    @Override
    public char getTaskType() {
        return 'D';
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
    public String getLast(){
        this.by = by;
        return by;
    }
}