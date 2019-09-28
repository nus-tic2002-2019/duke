import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        split_line();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        split_line();

        chatting();
    }

    public static void split_line() {
        System.out.println("--------------------------------------------------------");
    }

    public static void chatting() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        split_line();

        if(line.toLowerCase().equals ("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            split_line();
            return;
        }
        else {
            System.out.println(line);
            split_line();
            chatting();
        }
    }
}