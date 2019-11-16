import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadlines extends Task {

    //protected String by;
    protected LocalDate by;

    public Deadlines(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
        //return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public void print_Task() {
        System.out.println("[D]" + super.toString() + "by" + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")");
    }
}
