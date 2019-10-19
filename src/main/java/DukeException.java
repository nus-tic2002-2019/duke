public class DukeException extends Exception{

    public DukeException(String action) {
        super("\t________________\n\t OOPS!!! The description of a " + action.trim() + " cannot be empty.\n\t____");
    }
}
