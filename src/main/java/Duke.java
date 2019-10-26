import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;
    //private static Scanner in = new Scanner(System.in);


    //level 3
    public static void addTask(Task t) {
        tasks[taskCount] = t;
        System.out.println("\t--------------------------------------------------");
        System.out.println("added: " + t.getDescription());
        System.out.println("\t--------------------------------------------------");
        taskCount++;
    }

    public static void displayTasks() {
        System.out.println("\t--------------------------------------------------");
        for (int i = 0; i < taskCount; i++) {
            //System.out.println(i + 1 + ". [" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
            System.out.println(i + 1 + ". " + tasks[i]);
        }
        System.out.println("\t--------------------------------------------------");
    }



    //level 4
    public static void addTask(Todo t) {
        tasks[taskCount] = t;
        System.out.println("\t--------------------------------------------------");
        System.out.println("Got it. I've added this task: ");
        System.out.println(t);
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list");
        System.out.println("\t--------------------------------------------------");
    }

    public static void addTask(Deadlines t) {
        tasks[taskCount] = t;
        System.out.println("\t--------------------------------------------------");
        System.out.println("Got it. I've added this task: ");
        System.out.println(t);
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list");
        System.out.println("\t--------------------------------------------------");
    }

    public static void addTask(Events t) {
        tasks[taskCount] = t;
        System.out.println("\t--------------------------------------------------");
        System.out.println("Got it. I've added this task: ");
        System.out.println(t);
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list");
        System.out.println("\t--------------------------------------------------");
    }

    public static void markDone(int pos) {
        tasks[pos - 1].markDone(true);
        System.out.println("\t--------------------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("    [" + tasks[pos - 1].getStatusIcon() + "] " + tasks[pos - 1].getDescription());
        System.out.println("\t--------------------------------------------------");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");



        String line;
        String [] line_arr;

        while (true) {
            Scanner in = new Scanner(System.in);
            int index_date;
            line = in.nextLine();
            line_arr = line.split(" ", 2);
            switch (line.toUpperCase()) {

                case "LIST":
                    displayTasks();
                    break;
                case "BYE":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;

                default:

                    //if (s2[0].toUpperCase().equals("DONE")) {
                    //    setDone(Integer.parseInt(s2[1]));
                    //} else {
                    //    addTask(new Task(s1));
                    //}
                    switch (line_arr[0].toUpperCase()) {
                        case "DONE":
                            markDone(Integer.parseInt(line_arr[1]));
                            break;
                        case "TODO":
                            addTask(new Todo(line_arr[1]));
                            break;
                        case "DEADLINE":
                            String lineDeadline[] = line_arr[1].split("/by", 2);
                            addTask(new Deadlines(lineDeadline[0], lineDeadline[1]));
                            break;
                        case "EVENT":
                            //String lineEvent[] = s2[1].split("/at", 2);
                            //addTask(new Events(lineEvent[0],lineEvent[1]));
                            String lineEvent[] = line_arr[1].split("/at", 2);
                            addTask(new Events(lineEvent[0],lineEvent[1]));

                            break;
                        default:
                            addTask(new Todo(line));
                    }

                    break;
            }
        }
    }
}