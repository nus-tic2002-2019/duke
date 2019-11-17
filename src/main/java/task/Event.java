package duke.task;

import duke.ui.Ui;

public class Event extends Todo {
    private String at;

    public Event(String description,String at) {
        super(description);
        this.at = at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getAt() {
        return Ui.printDateTime(this.at);
    }
    
    public String printTask(){
      return ("[E][" + this.getStatusIcon() + "] " + this.description + "(at: " + this.getAt() + ")");
    }
    
    public String writeTask(){
      return ("E | " + this.getStatus() + " | " + this.description + " | " + this.getAt());
    }
    
    public String getDateTime(){
        return this.at;
    }
}
