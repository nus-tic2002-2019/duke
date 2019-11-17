package Duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
//    String t;
    String t1;
    public Deadline(String description, String time)
    {
        super(description);
//        t = time;
        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime d1 = LocalDateTime.parse(time.trim(), f1);
        DateTimeFormatter f2 = DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm");
        t1 = d1.format(f2);

    }

    public String getStatusIcon() {

        return ("[D]" + super.getStatusIcon()); //return tick or X symbols
    }

    public String getDescription() {

        return (super.getDescription()+" (by: "+t1+")");
    }

}
