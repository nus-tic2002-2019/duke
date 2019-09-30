public class Event extends Task {
    public Event (String content) {
        super(content);
    }


    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
