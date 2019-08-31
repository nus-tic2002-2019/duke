import java.util.Scanner;

public class Duke {
    public static void DukeReply(String replies) {
        System.out.println("\t________________________________________________________________\n" + "\t" + replies + "\n\t________________________________________________________________\n");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        DukeReply("Hello! I'm Duke\n\tWhat can i do for you?");
        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                DukeReply("Bye. Hope to see you again soon!");
                break;
            }
            DukeReply(input);
        }
    }
}



