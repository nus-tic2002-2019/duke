package Exceptions;

public class DukeEmptyException extends Exception {

    public DukeEmptyException(String input){
        super("     ☹ OOPS!!! The description of a " + input + " cannot be empty.");
    }
}
