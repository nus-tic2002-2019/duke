import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String separateLine="_____________________________________________";
        System.out.println(separateLine);
        System.out.println("Hello! I am Duke\nWhat can I do for you?");
        System.out.println(separateLine);

        String line="";
        String list="list";
        Scanner in =new Scanner(System.in);
        while(!( line = in.nextLine()).equals("bye")) {
            if(!line.equals("list")) {
                System.out.println(separateLine + "\n" + line + "\n" + separateLine);
            }
            else
            {
                System.out.println(separateLine);
                System.out.println(list);
                System.out.println(separateLine);
            }
        }
        if (line.equals("bye")) {
            System.out.println(separateLine+"\nBye. Hope to see you again soon!");
            System.exit(0);
        }


    }
}
