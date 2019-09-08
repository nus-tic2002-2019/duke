import java.util.Scanner;

public class Duke {
    private static String[] list = new String[100];
    private static int listCounter = 0;

    public static void addToList(String input) {
        list[listCounter] = input;
        listCounter++;
    }

    public static void greet() {
        String greet = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________";

        System.out.println(greet);
    }

    public static void echoOrExitorAdd() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        if (input.equals("bye") ) {
            String bye = "____________________________________________________________\n"
                    + "Bye. Hope to see you again soon!\n"
                    + "____________________________________________________________";
            System.out.println(bye);
            return;
        } else if (input.equals("list") ) {
            System.out.println("____________________________________________________________");
            for (int i = 0; i < listCounter; i++) {
                System.out.print(i + 1);
                System.out.print(". ");
                System.out.println(list[i]);
            }
            System.out.println("____________________________________________________________");
        } else {
            addToList(input);
            String echo = "____________________________________________________________\n"
                    + "added: " + input
                    + "\n____________________________________________________________";
            System.out.println(echo);
        }

        echoOrExitorAdd();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Bye!");
        greet();
        echoOrExitorAdd();
    }
}
