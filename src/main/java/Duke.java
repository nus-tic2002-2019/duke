import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static void printList(String[] list) {
        int index = 1;
        String graphicalFormatStart = ("    "
                + "____________________________________________________________"
                + "\n"
                + "    ");

        System.out.println(graphicalFormatStart);
        for(String elements : list) {
            System.out.println("    " +index + "." + " " + elements);
            index++;
        }
        System.out.println("    " + "____________________________________________________________");
    }

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
        // 08-Sep-2019.
        String[] content = new String[100];
        int counter = 0;

        String graphicalFormatStart = ("    "
                + "____________________________________________________________"
                + "\n"
                + "    ");

        String graphicalFormatEnd = ("\n" + "    " + "____________________________________________________________");


        System.out.println("Hello from" + nextLine + logo);
        System.out.println(spaces + line);
        System.out.println(spaces
                            +"Hello! I'm Duke"
                            + nextLine + spaces
                            + "What can I do for you?"
                            + nextLine + spaces + line);

/**********************************************************************************************************************
 * Level One + Level Two
 **********************************************************************************************************************/
        // User Response
        //String userResponse = scanInput.nextLine();
        while (true) {
            String userResponse = scanInput.nextLine();
            if (userResponse.equals("bye")) {
                break;
            } else if (userResponse.equals("list")) {
                content = Arrays.copyOf(content, counter);
                printList(content);
                content = Arrays.copyOf(content,100); // restating the arraySize to cater to more inputs
            } else {
                content[counter] = userResponse;
                counter++;
                System.out.println(graphicalFormatStart + "added: " + userResponse + graphicalFormatEnd);
            }
        }
        System.out.println(graphicalFormatStart
                            +"Bye. Hope to see you again soon!"
                            + graphicalFormatEnd);

        content = Arrays.copyOf(content, counter);
        //System.out.println(Arrays.toString(content)); // use to check variables in arrays.
    }
}
