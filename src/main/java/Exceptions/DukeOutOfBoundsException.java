package Exceptions;

public class DukeOutOfBoundsException extends  Exception{
    public DukeOutOfBoundsException(String input){
        super("     ☹ OOPS!!! The description of a " + input + " cannot be empty.");
    }
}

