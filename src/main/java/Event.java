public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String saveTask() {
        int isDone;
        if(super.isDone)
            isDone = 1;
        else
            isDone = 0;
        return "E | " + isDone + " | " + super.saveTask() + " | " + at;
    }
}
