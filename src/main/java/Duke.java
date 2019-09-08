import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

/**********************************************************************************************************************
 * Variables.
 **********************************************************************************************************************/

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String line = "____________________________________________________________";
        String spaces = "    ";
        String nextLine = "\n";
        Scanner scanInput = new Scanner(System.in);

        System.out.println("Hello from" + nextLine + logo);
        System.out.println(spaces + line);
        System.out.println(spaces+"Hello! I'm Duke"+ nextLine + spaces + "What can I do for you?"+ nextLine + spaces + line);

/**********************************************************************************************************************
 * Level One
 **********************************************************************************************************************/
        // User Response
        //String userResponse = scanInput.nextLine();
        while (true) {
            String userResponse = scanInput.nextLine();
            if(userResponse.equals("bye")) {
                break;
            } else {
                //arrayToCaptureInputs[counter] = userResponse;
                System.out.println(spaces + line + nextLine + spaces + userResponse + nextLine + spaces + line);
            }

        }
        System.out.println(spaces + line + spaces + nextLine + spaces +"Bye. Hope to see you again soon!"+ nextLine + spaces + line);
    }

/**********************************************************************************************************************
 * Level Two
 **********************************************************************************************************************/



}
