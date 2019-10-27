package Ui;
import TaskList.*;

public class Ui {
    public static String seperatorLine = "___________________________________________\n";
    public static String seperatorLine2 = "________________________________________\n";

    public static void welcome() {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.print(seperatorLine);
        System.out.println("Hello! I'm Duke\n"
                + "What can I do for you");
        System.out.println(seperatorLine);
    }

    public static void keywordError() {

        System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void byeMessage() {
        System.out.print(seperatorLine);
        System.out.println("       " + "Bye. Hope to see you again soon!");
        System.out.println(seperatorLine);
    }

    public static void todoError() {
        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
    }

    public static void deadlineDateError() {
        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
    }

    public static void eventDateError() {
        System.out.println("☹ OOPS!!! The event description cannot be empty.");
    }

    public static void doneNumberEmptyError() {
        System.out.println("☹ OOPS!!! The number of a done cannot be empty.");
    }

    public static void doneNumberInvalid() {
        System.out.println("Please enter which integer after delete ");

    }

    private static void print_event() {


    }
}
