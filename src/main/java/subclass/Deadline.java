/**
 * Deadline type tasks only accepts date of specified format
 * @param format1 defines what format the date input by user is
 * @return Deadline object added successfully into list
 * @throws java.text.ParseException if incorrect date format is entered
 */

package subclass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Deadline extends Task {
    private Date date_by;
    private SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yy hh:mm a");

    public Deadline(String description, String by) throws ParseException {
        super(description);

        date_by = format1.parse(by);
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + format1.format(date_by) + ")";
    }


}
