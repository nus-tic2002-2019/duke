/*
DuckException.java
define event task.
*/
package Duke.command;

public class DukeException extends Exception {
    /*
    constructor
    @param msg customized error message.
    @return error message details.
    */
    public DukeException(String msg){
        super(msg);
    }
}
