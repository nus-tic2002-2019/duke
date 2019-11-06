package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    protected LocalDateTime dateAndTime;
    protected String stringDateTime;

    private int getDaysToAdd(String details) {
        details = details.toLowerCase();
        if (details.contains("mon") || details.contains("today")) {
            return 1;
        }
        if (details.contains("tues") || details.contains("tomorrow")) {
            return 2;
        }
        if (details.contains("wednes")) {
            return 3;
        }
        if (details.contains("thurs")) {
            return 4;
        }
        if (details.contains("fri")) {
            return 5;
        }
        if (details.contains("satur")) {
            return 6;
        }
        if (details.contains("sun")) {
            return 7;
        }
        return 0;
    }

    public Deadlines(String description, String details, boolean readFromFile) {
        super(description);
        String changedDate;
        DateTimeFormatter formatter;
        LocalDateTime formattedDate = null;
        int daysToAdd = getDaysToAdd(details);
        if (readFromFile) {
            formatter = DateTimeFormatter.ofPattern("d MMM yyyy HHmm");
            formattedDate = LocalDateTime.parse(details, formatter); // change string to date
        } else {
            if (daysToAdd <= 0) {
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"); // set date input format
                formattedDate = LocalDateTime.parse(details, formatter); // change string to date
            } else {
                formattedDate = formattedDate.now();
                formattedDate = formattedDate.plusDays(daysToAdd);
            }
        }
        changedDate = formattedDate.toString(); // new string date
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
