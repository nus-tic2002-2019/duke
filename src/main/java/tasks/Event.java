package tasks;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    LocalDate on;
    LocalTime at;

    public Event(String description, LocalDate on, LocalTime at) {
        super(description);
        this.on = on;
        this.at = at;
    }

    @Override
    public String getDate(){
        return on.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String getTime() {
        return at.format(DateTimeFormatter.ofPattern("h:mma"));
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (on: " + getDate() + " at: " + getTime() + ")";
        //return "[T]" + super.toString();
    }
}
