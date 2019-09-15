import java.util.Arrays;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        String horizontal_line = ("____________________________________\n");
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(horizontal_line + "Hello I'm Duke\nWhat can I do for you? \n" + horizontal_line);

        Scanner in = new Scanner(System.in);
        String line;
        line = in.nextLine();

        // Level 2
        String[] list = new String [50];
        int count = 0;
        while (!line.equals("bye")) {

             if(line.equals("list")) {
                 System.out.print(horizontal_line);
                 for (int i = 0; i < count ; i++) {
                     System.out.println(i + 1 + "." + list[i]);
                 }
                 System.out.println(horizontal_line);
                 line = in.nextLine();
             } else {
                 list[count] = line;
                 System.out.println(horizontal_line + "Added: " + list[count] + '\n' + horizontal_line);
                 line = in.nextLine();
                 count++;
             }
        } System.out.println(horizontal_line + "Bye. Hope to see you again soon!" +'\n'+ horizontal_line);

        // Level 1
        // while(!line.equals(end)) {
        //    System.out.println(horizontal_line + line + '\n'+ horizontal_line);
        //    line = in.nextLine();
        //} System.out.println(horizontal_line + "Bye. Hope to see you again soon!" +'\n'+ horizontal_line);
    }
}
