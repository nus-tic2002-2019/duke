import java.util.Arrays;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am Duke");
        System.out.println("What can I do for you\n" + logo);

        Scanner in = new Scanner(System.in);
        System.out.print("");
        String line = in.nextLine();
        String result = "";
        int i=0;
        while (! line.equals("bye"))  { // && (! line.equals("list")))  {
            if (!line.equals("list")) {
                //line = in.nextLine();
                result += line + ",";
                System.out.println("added: " + line);
                //line = in.nextLine();
            }    else if (line.equals("list")) {
                 System.out.println(result.replace(",","\n"));
            }
            line = in.nextLine();
                  // for (listing : list
        }
        if (line.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}

