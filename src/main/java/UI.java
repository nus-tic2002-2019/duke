import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {

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

    public static void split_line(){
        System.out.println("--------------------------------------------------------");
    }

    public static void list_message(ArrayList<Task> tasks, int size){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < size; i++) {
            System.out.println(i + 1 + "." + tasks.get(i).toString());
        }
    }

    public static void add_message(Task t, int size){
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + t.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public static void done_message(ArrayList<Task> tasks, int index){
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(index).toString());
    }

    public static void delete_message(ArrayList<Task> tasks, int index){
        int size = tasks.size() - 1;
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  "  + tasks.get(index));
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public static void bye_message(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static String readCommand(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }


}
