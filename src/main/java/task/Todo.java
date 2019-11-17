package duke.task;

import duke.task.Task;

public class Todo extends Task{
  
  public Todo (String description){
    super(description);
  }

  public String printTask(){
      return ("[T][" + this.getStatusIcon() + "] " + this.description );
  }
  
  public String writeTask(){
      return ("T | " + this.getStatus() + " | " + this.description );
  }
}
