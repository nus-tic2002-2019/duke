public class Events extends Task {
    protected String at;

    public Events(String thingsToDo, String at) {
        super(thingsToDo);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
