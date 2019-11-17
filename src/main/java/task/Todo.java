package duke.task;

import duke.task.Task;
import priority.Priority;

public class Todo extends Task{
  
  public Todo (String description, Priority p){
    super(description,p);
  }

  public String printTask(){
      return ("[T][" + this.getStatusIcon() + "] " + this.description + " | " + getp() );
  }
  
  public String writeTask(){
      return ("T | " + this.getStatus() + " | " + this.description + " | " + getp() );
  }
  
  public String getDateTime(){
        return "";
  }
}
