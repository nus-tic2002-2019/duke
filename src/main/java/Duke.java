import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" + "Hello! I'm Duke\n" + "What can I do for you?\n" + "____________________________________________________________\n");
        Scanner input = new Scanner(System.in);
        String text = "";
        while ( !text.equals("bye") ) {
            text = input.nextLine();
            switch ( text ) {
                case "bye":  System.out.println("____________________________________________________________\n" + "Bye. Hope to see you again soon!\n" + "____________________________________________________________");
                    break;
                default: System.out.println("____________________________________________________________\n" + text +"\n" + "____________________________________________________________");
            }
        }
    }
}
