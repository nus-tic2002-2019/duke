package main.java.duke.exception;

/**
 * Signals that an operation would have violated the 'no duplicates' task of the list.
 */
public class DuplicateTaskException extends Exception{

public DuplicateTaskException() { super("There is a same task which already existed in the task list.");  }

}
