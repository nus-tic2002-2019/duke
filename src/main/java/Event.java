/*
 *  Event.java
 *  Defines the Event task.
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String at;

    /*
     * Constructs Event object
     * @param description Task description.
     * @param at Task's event date and time.
     */
    public Event(String description, String at) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime DateTime = LocalDateTime.parse(at,formatter);
        this.at = DateTime.format(formatter);
    }

    /*
     * Constructs Event object
     * @param description Task description.
     * @param at Task's event date and time.
     * @param status Task's completion status.
     */
    public Event(String status, String description, String at) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime DateTime = LocalDateTime.parse(at,formatter);
        this.at = DateTime.format(formatter);
        this.setStatus(status);
    }

    /*
     * Returns Event task
     * @return 'E' Event task.
     */
    @Override
    public char getTaskType() {
        return 'E';
    }

    /*
     * Returns Event message
     * @return details of Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }

    /*
     * Returns Event Date and time
     * @return Datetime in yyyy-MM-dd HH:mm format.
     */
    public String getLast(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime DateTime = LocalDateTime.parse(at,formatter);
        this.at = DateTime.format(formatter);
        return at;
    }

    /*
     * Set Event's date and Time.
     */
    public void setLast(String at) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime DateTime = LocalDateTime.parse(at,formatter);
        this.at = DateTime.format(formatter);
    }

}