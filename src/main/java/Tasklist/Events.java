package tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    protected boolean isDone;
    protected LocalDateTime at;

    /**
     * Constructor of event task.
     * @param description description of even task
     * @param at LocalDateTime of event date and time
     */
//    public Events(String description, String at)
    public Events(String description, LocalDateTime at)
    {
        super(description);
        isDone=false;
        this.at = at;
    }

    /**
     * To mark this deadline as completed
     * @param done boolean status whether to mark the status as completed
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * To obtain information whether the status is completed or not
     * @return boolean of status completion
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * To obtain the date of the event object
     * @return LocalDateTime of the date
     */
    public LocalDateTime getDateTime(){
        return this.at;
    }

    /**
     * To geenrate event object in readable format and display it to the user.
     * @return String human readbable format of deadline
     */
    @Override
    public String toString() {
        return "[E] [" + super.getStatusIcon() +"]" + super.getDescription() + "(at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy  HH:mma")) + ")" ;
    }

    /**
     * To generate string for which to write event object into file over a specific format in | separated
     * @return String event object in string
     */
    @Override
    public String writeToFile()
    {
        //return "E | " + isDone + " | " + this.getDescription();
        return "E" + super.writeToFile() + super.getDescription() + " | " + at.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }
}
