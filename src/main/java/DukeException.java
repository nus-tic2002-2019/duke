import java.util.*;

public class DukeException extends Exception {
    public DukeException(String errMsg) {
        //super(errMsg);
        System.out.println("OPPS! " + errMsg);
        System.out.println("__________________________________________________________________________\n");
    }
}