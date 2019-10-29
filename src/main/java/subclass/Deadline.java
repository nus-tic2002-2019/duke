/*
Deadline type tasks only accepts date of specified format
 */

package subclass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Deadline extends Task {
    protected Date date_by;
    SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yy hh:mm a");

    public Deadline(String description, String by) throws ParseException {
        super(description);

        date_by = format1.parse(by);
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + format1.format(date_by) + ")";
    }


}
