package tasklist;

import tasklist.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Event extends Task {
    protected String strDate;
    protected LocalDateTime Date;
    private DateTimeFormatter newFormat = new DateTimeFormatterBuilder()
            .appendPattern("MMM d yyyy[ HH:mm]")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();
    DateTimeFormatter formatOnFile = DateTimeFormatter.ofPattern("yyyy-MM-dd [HH:mm]");


    public Event(String description, String strDate) {
        super(description);

        this.Date = LocalDateTime.parse(strDate, formatOnFile);
    }

    public Event(String description, LocalDateTime Date, boolean isDone) {
        super(description);
        this.Date = Date;
        super.isDone = isDone;
    }

    public Event(String description, String strDate, boolean isDone) {
        super(description);
        this.Date = LocalDateTime.parse(strDate, formatOnFile);
        //this.Date = Date;
        super.isDone = isDone;
    }

    @Override
    public String getDescription() {
        return "[E]" + "[" + getStatusIcon() + "]" + super.getDescription() + " (at:" + Date.format(newFormat) + ")";
    }

    public String saveToFile(){
        int taskStatus = isDone ? 1:0;
        return "E | " + taskStatus + " | " + super.getDescription() + " | " + Date.format(formatOnFile) + "\r";
    }
}
