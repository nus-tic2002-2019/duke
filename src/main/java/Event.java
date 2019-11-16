import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime DateTime = LocalDateTime.parse(at,formatter);
        this.at = DateTime.format(formatter);
    }
    public Event(String status, String description, String at) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime DateTime = LocalDateTime.parse(at,formatter);
        this.at = DateTime.format(formatter);
        this.setStatus(status);
    }

    @Override
    public char getTaskType() {
        return 'E';
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }

    public String getLast(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime DateTime = LocalDateTime.parse(at,formatter);
        this.at = DateTime.format(formatter);
        return at;
    }
}