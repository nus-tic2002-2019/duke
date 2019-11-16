import java.time.LocalDate;
//import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected  String by;
    //protected LocalDate by;
    //protected LocalDateTime timing;
    protected String timing;

    public Event (String description, String by) {
        super(description) ;
        this.by = by;
        //this.by = LocalDate.parse(by.substring(0,10));
        //this.timing = by.substring(11);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + by + ")";
        //return "[E]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + timing + ")";
    }

    @Override
    public void print_Task() {
        System.out.println("[E]" + super.toString() + " (by: " + by + ")");
        //System.out.println("[E]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + timing + ")");
    }
}
