//level 7.more oop
/**
 *  Event command for storing Data "description" and "Date & Time"
 */

package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    //private boolean isDone;
        //private String at;
    private LocalDateTime at;
   // private String description;

    public Events(String description, LocalDateTime at) {
        super(description);
        //isDone = false;
        this.at = at;
    }

    public LocalDateTime getDate() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}



/**
public class Events extends Task {
    private boolean isDone;
    private String at;

    public Events(String description, String at){
        super(description);
        isDone = false;
        this.at = at;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString(){
        return "[E] [" + super.getStatusIcon() + "] " + super.getDescription() + "(at: " + at + ")";
    }

    @Override
    public String SaveFile(){

        return "E" + super.SaveFile() + super.getDescription() + " | " + at;
    }

}

**/