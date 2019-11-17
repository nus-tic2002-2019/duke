/*
DukeException.java
define Exception task.
*/

package exception;

public class Exception extends java.lang.Exception {
    /*
constructor
@param msg customized error message.
@return error message details.
*/
    public Exception(String message) {
        super(message);
    }
}