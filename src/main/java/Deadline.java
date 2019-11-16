/*
 *  Deadline.java
 *  Defines the Deadline task.
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;

    /*
     * Constructs Deadline object
     * @param description Task description.
     * @param by Task's deadline in datetime.
     */
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(by,formatter);
        this.by = dateTime.format(formatter);
    }

    /*
     * Constructs Deadline object
     * @param description Task description.
     * @param by Task's deadline in datetime.
     * @param status Task's completion status.
     */
    public Deadline(String status,String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(by,formatter);
        this.by = dateTime.format(formatter);
        this.setStatus(status);
    }

    /*
     * Returns Deadline task
     * @return 'D' Deadline task.
     */
    @Override
    public char getTaskType() {
        return 'D';
    }

    /*
     * Returns Deadline message
     * @return details of Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }

    /*
     * Returns Deadline Datetime
     * @return Datetime in yyyy-MM-dd HH:mm format.
     */
    public String getLast(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(by,formatter);
        this.by = dateTime.format(formatter);
        return by;
    }

    /*
     * Set Deadline's date and Time.
     */
    public void setLast(String by) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(by,formatter);
        this.by = dateTime.format(formatter);
    }
}