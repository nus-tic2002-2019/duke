import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Stores the message to interact with user.
 * */
public class UI {
    /**
     * Prints welcome message of duke.
     * */
    public static void welcome(){
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
    }
    /**
     * Prints out split line.
     * */
    public static void split_line(){
        System.out.println("--------------------------------------------------------");
    }
    /**
     * Prints list message of task list.
     * @param tasks task list for printing.
     * */
    public static void list_message(ArrayList<Task> tasks){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i).toString());
        }
    }
    /**
     * Prints add message of duke.
     * @param t task to print after adding to list.
     * @param size size of task list.
     * */
    public static void add_message(Task t, int size){
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + t.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }
    /**
     * Prints done message of duke.
     * @param tasks task list.
     * @param index index of task in task list which will be marked as done.
     * */
    public static void done_message(ArrayList<Task> tasks, int index){
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(index).toString());
    }
    /**
     * Prints delete message of duke.
     * @param tasks task list.
     * @param index index of task in task list which will be deleted.
     * */
    public static void delete_message(ArrayList<Task> tasks, int index){
        int size = tasks.size() - 1;
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  "  + tasks.get(index));
        System.out.println("Now you have " + size + " tasks in the list.");
    }
    /**
     * Prints bye message of duke.
     * */
    public static void bye_message(){
        System.out.println("Bye. Hope to see you again soon!");
    }
    /**
     * Scan and reads the input of user.
     * @return user full command in string.
     * */
    public static String readCommand(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

}
