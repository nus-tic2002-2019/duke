public class Todo extends Task {
    public Todo(String description){
        super(description);
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       [T][✗] " + description + "\n" +
                "     Now you have " +  Integer.toString(Duke.current) +  " tasks in the list.\n" +
                "    ____________________________________________________________\n");
    }

    public void print(){
        System.out.println("[T][" + getStatusIcon() + "] " + this.description);
    }
}
