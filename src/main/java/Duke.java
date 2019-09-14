import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String horizontal_line =  ("____________________________________\n");
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
        String end = new String ("bye");
        while(!line.equals(end)) {
            System.out.println(horizontal_line + line + '\n'+ horizontal_line);
            line = in.nextLine();
        } System.out.println(horizontal_line + "Bye. Hope to see you again soon!" +'\n'+ horizontal_line);

    }
}
