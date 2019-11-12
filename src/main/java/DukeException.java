public class DukeException extends Exception {

    private String message = "\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    public DukeException(String message) {
        this.message = "\t☹ OOPS!!! The description of a " + message + " cannot be empty.";
    }

    public DukeException() {
      this.message = message;
    }

    public String getMessage(){
      return message;
    }
}