package duke.task;

import duke.priority.Priority;

/**
 * Todo class is an extension class of Tasks
 * It manages all todo items stored by the user
 */
public class Todo extends Task{
    /**
    * Constructor method for Todo class
    * @param description Describe the nature of the todo
    * @param p priority of the deadline (Low, Medium, High)
    */
    public Todo (String description, Priority p){
      super(description,p);
    }
    /**
    * Overrides the method printTask in the Task class
    * @return the string to be printed
    */
    
    @Override
    public String printTask(){
        return ("[T][" + this.getStatusIcon() + "] " + this.description + " priority: " + getp() );
    }
    /**
    * Override the method writeTask in the Task class
    * @return The text to be saved in the file
    */
    @Override
    public String writeTask(){
        return ("T | " + this.getStatus() + " | " + this.description + " | " + getp() );
    }
    
    /**
    * Get the todo date in datetime format
    * @return As todo doesn't have a date description, return null.
    */
    @Override
    public String getDateTime(){
          return "";
    }
}
