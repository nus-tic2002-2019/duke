public class Deadline extends Task {
    protected String by;

    public Deadline(String input, String by) {
        super(input);
        this.by = by;
    }

    //Accessor
    @Override
    public String printTask() {
        return "[D]" + super.printTask() + " (by: " + by + ")";
    }
}
