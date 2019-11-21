package Exceptions;

public class InvalidDateException extends Exception {
    public InvalidDateException(String msg){
        super("     ☹ OOPS!!! Date : " + msg + "Syntax Wrong, Please use : DD-MMMM-YYYY(13-Oct-2019) HHmm (1000)");
    }
}
