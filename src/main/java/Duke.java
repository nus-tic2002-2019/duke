import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        Scanner in = new Scanner(System.in);

        String s1;
        String s2;

        s1 = in.nextLine();
        s2 = "bye";

        if (s1.equalsIgnoreCase(s2)) {
            System.out.println("Bye. Hope to see you again soon!");


        } else {
            System.out.println(s1);
        }
    }
}