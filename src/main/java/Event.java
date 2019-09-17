public class Event extends Task {
    public Event(String description){
        super(description);
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       [E][✗] " + description + "\n" +
                "     Now you have " +  Integer.toString(Duke.current) +  " tasks in the list.\n" +
                "    ____________________________________________________________\n");

    }

    public void print(){
        System.out.println("[E][" + getStatusIcon() + "] " + this.description);
    }
}
