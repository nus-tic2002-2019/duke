import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime DateTime = LocalDateTime.parse(by,formatter);
        this.by = DateTime.format(formatter);
    }
    public Deadline(String status,String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime DateTime = LocalDateTime.parse(by,formatter);
        this.by = DateTime.format(formatter);
        this.setStatus(status);
    }

    @Override
    public char getTaskType() {
        return 'D';
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
    public String getLast(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime DateTime = LocalDateTime.parse(by,formatter);
        this.by = DateTime.format(formatter);
        return by;
    }
}