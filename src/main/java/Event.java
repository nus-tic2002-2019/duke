public class Event extends Task {
    protected String startend;

    public Event(String input, String startend)
    {
        super(input);
        this.startend = startend;
    }

    @Override
    public String printTask()
    {
        return "[E]" + super.printTask() + " (at: " + startend + ")";
    }

}
