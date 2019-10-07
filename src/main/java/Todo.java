public class Todo extends Task {

    public Todo(String description){
        super(description);
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       [T][\u2718] " + description + "\n" +
                "     Now you have " +  Integer.toString(Duke.store.size() + 1) +  " tasks in the list.\n" +
                "    ____________________________________________________________\n");
    }

    @Override
    public String toString(){
        return "[T][" + getStatusIcon() + "] " + this.description;
    }
}
