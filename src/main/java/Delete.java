public class Delete extends Task{
    protected String to;

    public Delete(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

