import java.util.*;

import MyClasses.*;

public class Duke {

    private static ArrayList<TaskList> tasks = new ArrayList<>();

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

    public static void AddTask(TaskList task) {
        tasks.add(task);
    }

    public static boolean Task(String Cmmd) {

        ArrayList<String> commands = new ArrayList<>(Arrays.asList(Cmmd.split(" ", 3)));
        switch (commands.get(0).toLowerCase()) {
            case "bye":
            case "quit":
            case "close":
            case "exit":
                System.out.println("Bye. Hope to see you again soon!");
                PrintHL();
                return false;
            case "list":
                for (int i = 0; i < tasks.size(); i++) {
                    tasks.get(i).ListTask(i);
                }
                PrintHL();
                break;

            case "done":
            case "finish":
                    try {
                        if (isNumeric(commands.get(1))) {
                            tasks.get((Integer.parseInt(commands.get(1)) - 1)).DoneTask();
                        } else {
                            System.out.println("Please provide the index number");
                            PrintHL();
                        }
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Item at the specified index does not exist");
                    }

                break;
            case "add":
                if (commands.size() > 2 && commands.get(2) != null && !(commands.get(2).trim().isBlank())) {
                    if (commands.get(1).equals("-t") || commands.get(1).equals("task")) {
                        AddTask(new TaskList(commands.get(2)));
                    }
                    if (commands.get(1).equals("-d") || commands.get(1).equals("deadline")) {
                        String[] tasktime = commands.get(2).split("/by");
                        try {
                            AddTask(new Deadlines(tasktime[0], tasktime[1]));
                        } catch (IndexOutOfBoundsException e) {
                            AddTask(new Deadlines(tasktime[0]));
                        }

                    }
                    if (commands.get(1).equals("-e") || commands.get(1).equals("event")) {
                        String[] tasktime = commands.get(2).split("/at");
                        try {
                            AddTask(new Events(tasktime[0], tasktime[1]));
                        } catch (IndexOutOfBoundsException e) {
                            AddTask(new Events(tasktime[0]));
                        }
                    }
                } else {
                    System.out.println("OOPS!! Please type descriptions");
                    PrintHL();
                }
                break;
            case "remove":
            case "delete":
                System.out.println("Noted! I have removed this task:");
                try {
                    if (isNumeric(commands.get(1))) {
                        tasks.get(Integer.parseInt(commands.get(1)) - 1).ListTask();
                        tasks.remove(Integer.parseInt(commands.get(1)) - 1);
                    } else {
                        System.out.println(("Please type numeric only!"));
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("OOPS! the task number you specified does not exists");
                }
                break;
            default:
                System.out.println("OOPS!! I'm Sorry. I do not know what you meant by " + Cmmd + " (╥﹏╥) .");
                PrintHL();
        }
        return true;
    }

    public static void main(String[] args) {
        init();
        while (Task(ReadText())) ;
    }
}
