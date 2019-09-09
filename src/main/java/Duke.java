import java.util.Scanner;
import java.util.ArrayList;

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
        ArrayList<String> list = new ArrayList<String>();
        while ( !text.equals("bye") ) {
            text = input.nextLine();
            switch ( text ) {
                case "bye":  System.out.println("____________________________________________________________\n" + "Bye. Hope to see you again soon!\n" + "____________________________________________________________"); break;
                case "list": System.out.println("____________________________________________________________"); for (int i = 0; i < list.size(); i++){System.out.println((i+1)+". "+list.get(i));} System.out.println("____________________________________________________________\n"); break;
                default: System.out.println("____________________________________________________________\n" + "added: " + text +"\n" + "____________________________________________________________\n"); list.add(text);
            }
        }
    }
}
