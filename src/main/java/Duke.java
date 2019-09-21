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
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String line;
        int count = 0;
        Scanner input = new Scanner(System.in);
        line = input.toString();
        Task[] t = new Task[100];

        boolean isExit = false;
        while (!isExit) {
            line  = input.nextLine();
            switch (line.split(" ")[0]) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case "done":
                    int line_num = Integer.parseInt(line.split(" ")[1]);
                    for (int i=0; line_num != i; i++) {
                        t[line_num-1].markAsDone();
                        break;
                    }
                case "list":
                    ReturnList(t, count);
                    break;

                default:
                    t[count] = new Task(line);
                    StoreList(t, line, count);
                    count++;
                    break;
            }
        }
    }


    public static Task[] StoreList(Task[] t, String line, int number) {
        t[number] = new Task(line);
        System.out.println("added:" + line);
        return t;
    }

    public static void ReturnList(Task[] t, int number) {
        for (int i = 0; i < number; i++) {
            String icon = t[i].getStatusIcon();
            System.out.println(i + 1 + "." + "[" + icon + "]" + t[i].getDescription());
        }
    }
}