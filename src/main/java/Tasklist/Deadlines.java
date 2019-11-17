package tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadlines extends Task {

    protected boolean isDone;
    protected LocalDateTime by;

    /**
     * Deadline objects which extended from Task object
     * @param description Description of deadline description
     * @param by deadline date in LocalDateTime data type
     */
    public  Deadlines(String description, LocalDateTime by)
    {
        super(description);
        isDone=false;
        this.by = by;
        //System.out.println("Description:" + this.description + "; by: " + this.by);
    }

    /**
     * To mark this deadline as completed
     * @param done  mark the status as completed
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * To obtain the date of the deadline object
     * @return LocalDateTime of the date
     */
    @Override
    public LocalDateTime getDateTime(){
        return this.by;
    }

    /**
     * To get status of the deadline
     * @return boolean status of deadline true/false
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * To generate deadline object in readable format and display it to the user.
     *
     * @return String human readbable format of deadline object
     */
    @Override
    public String toString() {
            return "[D] [" + super.getStatusIcon() +"]" + super.getDescription()  + "(by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy  HH:mma")) + ")" ;
    }

    /**
     * To generate string for which to write event object into file over a specific format in | separated
     * @return String event object in string
     */
    @Override
    public String writeToFile()
    {
        return "D" + super.writeToFile() + super.getDescription() + " | " + by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")) ;
    }
}
