/*
Deadline.java
define deadline task.
*/
package Duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
//    String t;
    String t1;
    /*
    constructor of deadline object.
    @param description deadline task description
    @param time deadline of deadline object.
    */
    public Deadline(String description, String time)
    {
        super(description);
//        t = time;
        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime d1 = LocalDateTime.parse(time.trim(), f1);
        DateTimeFormatter f2 = DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm");
        t1 = d1.format(f2);

    }

    /*
    get deadline status by return tick or x symbols.
    @return tick or x symbols
    */
    public String getStatusIcon() {

        return ("[D]" + super.getStatusIcon()); //return tick or X symbols
    }

    /*
    get deadline description
    @return task description
    */
    public String getDescription() {

        return (super.getDescription()+" (by: "+t1+")");
    }

}
