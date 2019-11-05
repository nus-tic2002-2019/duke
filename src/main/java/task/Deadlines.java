package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    protected LocalDateTime dateAndTime;
    protected String stringDateTime;


    public Deadlines(String description, String details, boolean readFromFile) {
        super(description);
        String changedDate;
        if (!readFromFile) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"); // set date input format
            LocalDateTime formattedDate = LocalDateTime.parse(details, formatter); // change string to date
            changedDate = formattedDate.toString(); // new string date
        } else {
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("d MMM yyyy HHmm");
            LocalDateTime formattedDate = LocalDateTime.parse(details, formatter2); // change string to date
            changedDate = formattedDate.toString(); // new string date
        }
        this.dateAndTime = LocalDateTime.parse(changedDate); // new string date into date
        this.stringDateTime = dateAndTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"));
        this.type = 'D';
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public String getDetails() {
        return "(by: " + this.stringDateTime + ")";
    }
}
