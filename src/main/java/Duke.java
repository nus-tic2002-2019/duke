import java.util.*;
import MyClasses.TaskList;

public class Duke {

    public static final String HoriLine = "--------------------------------------------------\n";

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

    //check if string numeric
    public static boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }

    // Read the char input of the scanner;
    public static String ReadText() {
        Scanner sc = new Scanner(System.in);
        String data = sc.nextLine();
        PrintHL();
        return data;
    }

    public static boolean Task(String Cmmd) {
        TaskList task = new TaskList();
        ArrayList<String> commands = new ArrayList<String>(Arrays.asList(Cmmd.split(" ", 3)));
        System.out.println(commands);
        switch (commands.get(0).toLowerCase()) {
            case "bye":
            case "quit":
            case "close":
            case "exit":
                System.out.println("Bye. Hope to see you again soon!");
                PrintHL();
                return false;
            case "list":
                try {
                    if (commands.size() > 1) {
                        if (isNumeric(commands.get(1))) {
                            task.ListTask(Integer.parseInt(commands.get(1)));
                        } else {
                            System.out.println("Unknown Command for List : " + commands);
                            PrintHL();
                        }
                    } else {
                        task.ListTask();
                    }
                } catch (IndexOutOfBoundsException e) {
                    task.ListTask();
                }


                break;
            case "add":
                if (commands.get(1).equals("-t") || commands.get(1).equals("task")) {
                    task.AddTask(commands.get(2));
                }
                break;
            default:
                System.out.println("Unknown Command : " + Cmmd);
                PrintHL();
        }
        return true;
    }

    public static void main(String[] args) {
        init();
        while (Task(ReadText())) ;
    }
}
