public class Event extends Task {
    public Event(String description){
        super(description);
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       [E][\u2718] " + description + "\n" +
                "     Now you have " +  Integer.toString(Duke.store.size()) +  " tasks in the list.\n" +
                "    ____________________________________________________________\n");

    }

    @Override
    public String toString(){
        return "[E][" + getStatusIcon() + "] " + this.description;
    }
}
