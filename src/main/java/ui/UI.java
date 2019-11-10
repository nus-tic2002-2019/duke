package ui;

import tasklist.Task;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    public static void printDuke() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String line;
        ArrayList<Task> taskList = new ArrayList<Task>();
        Scanner in = new Scanner(System.in);
        System.out.print("Hello! I'm Duke\n" + "What can I do for you?");
    }
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
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

}
