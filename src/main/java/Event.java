package duke;

public class Event extends Todo {
    private String at;

    public Event(String description,String at) {
        super(description);
        this.at = at;
    }

    public void setAt(String by) {
        this.at = at;
    }

    public String getAt() {
        return this.at;
    }
    
    public String printTask(){
      return ("[E][" + this.getStatusIcon() + "] " + this.description + "(at: " + this.getAt() + ")");
    }
}
