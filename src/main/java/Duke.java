import java.util.*;

public class Duke {

    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        Duke d = new Duke();
        //System.out.println(d);

        System.out.println("\tHello! I'm Duke \n\tWhat can I do for you?");

        Scanner in = new Scanner(System.in);

        String echo = " ";
        while(echo != null) {
            echo = in.nextLine();
            if (echo.equals("bye")) // DO NOT use ==
                break;
            System.out.println("\t" + echo + "\n");
        }
        System.out.println("\tBye. Hope to see you again soon!");



    }

    /*
    @Override
    public String toString() {
        return "|"  + logo + "|" ;
    } */



}
