import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    //protected  String by;
    protected LocalDate by;
    //protected LocalDateTime timing;
    protected String timing;

    /**
     * Declare the
     *
     * @param description store the information of the content of the task
     * @param by store the timing where the task to be completed
     */
    public Event (String description, String by) {
        super(description) ;
        //this.by = by;
        this.by = LocalDate.parse(by.substring(0,10));
        this.timing = by.substring(11);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + by + " " +  timing + ")";
        //return "[E]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + timing + ")";
    }

    @Override
    public void print_Task() {
        System.out.println("[E]" + super.toString() + " (at: " + by + ")");
        //System.out.println("[E]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + timing + ")");
    }
}
