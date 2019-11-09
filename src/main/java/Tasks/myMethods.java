package Tasks;
import Exceptions.*;

import java.security.spec.ECField;
import java.util.concurrent.ExecutionException;

public class myMethods {
    public String[] parseSpace(String input){
        String[] act = input.split(" ",2);
        return act;
    }

    public String parseEvents(String input){
        int indexOfSlash = input.indexOf("/");
        String tmp0 = input.substring(0, indexOfSlash);
        return tmp0;
    }

    public String parseSlash(String input){

        try {
            if (input.indexOf("/") == 0) {
                System.out.println("@@@@@@@@@@@ PRINT THIS PARSE SLASH : " );
                throw new Exception("");
            }

        }

        catch (Exception e) {
            System.out.println("OOKOKOKOKOKOK ");
        }
        int indexOfSlash = input.indexOf("/");
        //String[] act = input.split("/",2);
        //String tmp0 = input.substring(7, indexOfSlash);
        String tmp1 = input.substring(indexOfSlash+1, indexOfSlash+3);
        String tmp2 = input.substring(indexOfSlash+3, input.length());
        return "(" + tmp1 + ": " + tmp2 + ")";
    }
}
