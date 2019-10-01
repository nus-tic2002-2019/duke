package duke;

public class Todo extends Task{
  private boolean isDone;
  
  public Todo (String description){
    super(description);
    this.isDone = false;
  }
  
  public void markAsDone (){
    this.isDone = true;
  }
  
  public boolean isDone (){
    return this.isDone;
  }
  
  public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
  }
}
