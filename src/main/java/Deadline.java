public class Deadline extends Task {
    public Deadline(String Description){
        super(Description);
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       [D][\u2718] " + description + "\n" +
                "     Now you have " +  Integer.toString(Duke.store.size() + 1) +  " tasks in the list.\n" +
                "    ____________________________________________________________\n");
    }

    @Override
    public String toString(){
        return "[D][" + getStatusIcon() + "] " + this.description;
    }
}
