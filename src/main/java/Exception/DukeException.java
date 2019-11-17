package exception;

public class DukeException extends Exception{
    /**
     * Return custom duke exception based on input message
     * @param message input message of exception to be returned to user
     */
    public DukeException(String message){
        super(message);
    }
}
