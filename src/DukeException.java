public class DukeException extends Exception{

    public DukeException(String input){
        super("\t____________________________________________________________\n\t ☹ OOPS!!! The description of a " + input.trim() + " cannot be empty.\n\t____________________________________________________________");
    }

    public DukeException(String input, String date){
        super("\t____________________________________________________________\n\t ☹ OOPS!!! The date of a " + input.trim() + " cannot be empty.\n\t____________________________________________________________");
    }
}