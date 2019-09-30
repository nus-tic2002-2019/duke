public class Deadline extends Task {
    public Deadline (String content) {
        super(content);
    }


    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
