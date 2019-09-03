import java.util.*;

import MyClasses.TaskList;
public class Duke {

    public static final String HoriLine = "--------------------------------------------------\n";
    TaskList List = new TaskList();
    public static void init() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        PrintHL();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        PrintHL();
    }

    public static void PrintHL() {
        System.out.println(HoriLine);
    }

    // Read the char input of the scanner;
    public static String ReadText() {
        Scanner sc = new Scanner(System.in);
        String data = sc.nextLine();
        PrintHL();
        return data;
    }

    public static boolean Task(String Cmmd) {
        switch (Cmmd.toLowerCase()) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                PrintHL();
                return false;
            default:
                System.out.println(Cmmd);
                PrintHL();
                return true;
        }
    }

    public static void main(String[] args) {
        init();
        while (Task(ReadText())) ;
    }
}
