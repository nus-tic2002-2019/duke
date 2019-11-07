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
        splitLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("Pls key in the file address with '\\'");
        splitLine();
    }
    /**
     * Prints the message to ask for command after loading file successfully.
     * */
    public static void askCommand() {
        splitLine();
        System.out.println("Load file successfully!");
        System.out.println("What can I do for you?");
        splitLine();
    }
    /**
     * Prints out split line.
     * */
    public static void splitLine(){
        System.out.println("--------------------------------------------------------");
    }
    /**
     * Prints list message of task list.
     * @param tasks task list for printing.
     * */
    public static void listMessage(ArrayList<Task> tasks) {
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
    public static void addMessage(Task t, int size) {
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + t.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }
    /**
     * Prints done message of duke.
     * @param tasks task list.
     * @param index index of task in task list which will be marked as done.
     * */
    public static void doneMessage(ArrayList<Task> tasks, int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(index).toString());
    }
    /**
     * Prints delete message of duke.
     * @param tasks task list.
     * @param index index of task in task list which will be deleted.
     * */
    public static void deleteMessage(ArrayList<Task> tasks, int index) {
        int size = tasks.size() - 1;
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  "  + tasks.get(index));
        System.out.println("Now you have " + size + " tasks in the list.");
    }
    /**
     * Prints bye message of duke.
     * */
    public static void byeMessage(){
        System.out.println("Bye. Hope to see you again soon!");
    }
    /**
     * Scan and reads the input of user.
     * @return user full command in string.
     * */
    public static String readCommand() {
        Scanner in = new Scanner(System.in);
        String fullCommand = in.nextLine();
        assert fullCommand.length() > 0: "The command can't be empty!";
        return fullCommand;
    }

}
