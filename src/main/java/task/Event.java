package duke.task;

import java.util.*;

public class Event extends Todo {
    private Date at;

    public Event(String description,Date at) {
        super(description);
        this.at = at;
    }

    public void setAt(Date at) {
        this.at = at;
    }

    public Date getAt() {
        return this.at;
    }
    
    public String printTask(){
      return ("[E][" + this.getStatusIcon() + "] " + this.description + "(at: " + this.getAt() + ")");
    }
    
    public String writeTask(){
      return ("E | " + this.getStatus() + " | " + this.description + " | " + this.getAt());
    }
}
