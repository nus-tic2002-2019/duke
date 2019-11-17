/*
 *  DukeException.java
 *  Defines the Exception of 'Duke'.
 */
public class DukeException extends Exception {

    //default exception message thrown
    private String message = "\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    /*
     * Returns exception message when description of task is empty.
     * @param message Type of task.
     */
    public DukeException(String message) {
        this.message = "\t☹ OOPS!!! The description of a " + message + " cannot be empty.";
    }

    /*
     *  Constructs Duke object.
     *  @return default message
     */
    public DukeException() {
      this.message = message;
    }

    /*
     * Returns exception message.
     * @return message Exception message.
     */
    public String getMessage(){
      return message;
    }
}