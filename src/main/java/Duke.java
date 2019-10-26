import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static Task[] tasks = new Task[100];
    private static int Count = 0;
    //private static Scanner in = new Scanner(System.in);


    //level 3
    public static void addTask(Task t) {
        tasks[Count] = t;
        System.out.println("\t--------------------------------------------------");
        System.out.println("added: " + t.getDescription());
        System.out.println("\t--------------------------------------------------");
        Count++;
    }

    public static void displayTasks() {
        System.out.println("\t--------------------------------------------------");
        for (int i = 0; i < Count; i++) {
            //System.out.println(i + 1 + ". [" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
            System.out.println(i + 1 + ". " + tasks[i]);
        }
        System.out.println("\t--------------------------------------------------");
    }



    //level 4
    public static void addTask(Todo t) {
        tasks[Count] = t;
        System.out.println("\t--------------------------------------------------");
        System.out.println("Got it. I've added this task: ");
        System.out.println(t);
        Count++;
        System.out.println("Now you have " + Count + " tasks in the list");
        System.out.println("\t--------------------------------------------------");
    }

    public static void addTask(Deadlines t) {
        tasks[Count] = t;
        System.out.println("\t--------------------------------------------------");
        System.out.println("Got it. I've added this task: ");
        System.out.println(t);
        Count++;
        System.out.println("Now you have " + Count + " tasks in the list");
        System.out.println("\t--------------------------------------------------");
    }

    public static void addTask(Events t) {
        tasks[Count] = t;
        System.out.println("\t--------------------------------------------------");
        System.out.println("Got it. I've added this task: ");
        System.out.println(t);
        Count++;
        System.out.println("Now you have " + Count + " tasks in the list");
        System.out.println("\t--------------------------------------------------");
    }

    public static void markDone(int post) {
        tasks[post - 1].markDone(true);
        System.out.println("\t--------------------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("    [" + tasks[post - 1].getStatusIcon() + "] " + tasks[post - 1].getDescription());
        System.out.println("\t--------------------------------------------------");
    }

    public static void main(String[] args) throws DukeException {
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

            try {
                switch (line.toUpperCase()) {

                    case "LIST":
                        displayTasks();
                        break;
                    case "BYE":
                        System.out.println("Bye. Hope to see you again soon!");
                        return;

                    default:

                        switch (line_arr[0].toUpperCase()) {
                            case "DONE":
                                markDone(Integer.parseInt(line_arr[1]));
                                break;
                            case "TODO":
                                try {
                                    addTask(new Todo(line_arr[1]));
                                } catch(IndexOutOfBoundsException e) {
                                    System.out.println("OOPS!!! The description of a Todo cannot be empty.");
                                }
                                break;
                            case "DEADLINE":
                                try {
                                    String lineDeadline[] = line_arr[1].split("/by", 2);
                                    addTask(new Deadlines(lineDeadline[0], lineDeadline[1]));
                                } catch(IndexOutOfBoundsException e) {
                                    System.out.println("OOPS!!! The description of a Deadline cannot be empty.");
                                }
                                break;
                            case "EVENT":
                                try {
                                    String lineEvent[] = line_arr[1].split("/at", 2);
                                    addTask(new Events(lineEvent[0], lineEvent[1]));
                                } catch(IndexOutOfBoundsException e) {
                                    System.out.println("OOPS!!! The description of a Event cannot be empty.");
                                }
                                break;
                            default:
                                //addTask(new Todo(line));
                                throw new DukeException();
                        }

                        break;
                }
            } catch (DukeException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(" );
            } catch (Exception e) {
                System.out.println("Exception caught" + e.getMessage());
            }

        }
    }
}