import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String[] new_list = new String[100];
        String line;
        Scanner in = new Scanner(System.in);
        int i = 0;
        line = in.nextLine();
        while (!line.equals("bye")){
            new_list[i] = line;
            System.out.println("added: " + line);
            if (line.equals("list")){
                for (int a = 0; a < i; a++){
                    System.out.println(new_list[a]);
                }
            }
            i++;
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
