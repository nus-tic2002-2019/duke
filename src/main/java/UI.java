import tasklist.Task;

import java.util.ArrayList;

public class UI {
    public static void printDuke() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        ArrayList<Task> taskList = new ArrayList<Task>();
        System.out.print("Hello! I'm Duke\n" + "What can I do for you?\n");
    }
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    public static void printInLine(String text) {
        System.out.println("    " + text);
    }

    public static void printListEmpty() {
        System.out.println("List is empty!");
    }

    public static void printTaskSaved() {
        System.out.println("Tasks saved!");
    }

    public static void printMarkedAsDone() {
        System.out.println( "    Nice! I've marked this task(s) as done:");
    }

    public static void printAddedTask(){
        System.out.println( "    Got it. I've added this task:");
    }

    public static void printNumberOfTasks( ArrayList<Task> taskList) {
        System.out.println("    Now you have " + (taskList.size()) + " tasks in the list.");
    }

    public static void printRemoveTask() {
        System.out.println( "    Noted. I've removed this task:");
    }

    public static void printTask(ArrayList<Task> taskList) {
        UI.printInLine(" " + taskList.get(taskList.size() - 1).toString());
    }

    public static void printOutput(ArrayList<Task> taskList) {
        UI.printLine();
        for (int i = 0; i < taskList.size(); i++) {
            UI.printInLine((i + 1) + ". " + taskList.get(i).toString());
        }
        UI.printLine();
    }

    public static void printStringFormatException() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void printNumberFormatException() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void printIndexOutOfRangeException() {
        System.out.println("☹ OOPS!!! Out of Range!");
    }

    public static void printEmptyException() {
        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
    }

    public static void printParseException() {
        System.out.println("☹ OOPS!!! Please enter DD MMM YYYY date format.");
    }







}
