package Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected boolean isDeadline;
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        isDeadline = false;
    }

    @Override
    public String toString(){
//        LocalDate d1 = LocalDate.parse(" 2019-12-01");
//        deadline meeting /by 2019-01-01

        String byDate  = by.trim();
        LocalDate d1 = LocalDate.parse(byDate);
        byDate = d1.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + super.toString() +  "(by:" + byDate  + ")" ;

//        return "[D]" + super.toString() +  "(by:" + by  + ")" ;
    }

}
