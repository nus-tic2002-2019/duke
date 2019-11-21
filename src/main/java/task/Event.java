package duke.task;

import duke.ui.Ui;
import priority.Priority;

public class Event extends Todo {
    private String at;

    public Event(String description, Priority p, String at) {
        super(description,p);
        this.at = at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getAt() {
        return Ui.printDateTime(this.at);
    }
    
    public String printTask(){
      return ("[E][" + this.getStatusIcon() + "] " + this.description + getp() + "(at: " + this.getAt() + ")");
    }
    
    public String writeTask(){
      return ("E | " + this.getStatus() + " | " + this.description  + " | " + getp() + " | " + this.getAt());
    }
    
    public String getDateTime(){
        return this.at;
    }
}
