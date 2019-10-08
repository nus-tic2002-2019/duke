package subclass;

import java.util.Scanner;

public class Ui {

    private Scanner in;

    public static void showWelcome () {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("\t_________________________________________");
        System.out.println("\t\tHello! I'm Duke");
        System.out.println("\t\tWhat can I do for you?");
        System.out.println("\t_________________________________________");
    }

    private boolean isInputting(String input) {
        return input.trim().isEmpty();
    }

    public String readCommand() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (isInputting(line)) {
            line = in.nextLine();
        }
        return line;
    }

    public static void displayError() {
        System.out.println("\t\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void markAsDone() {
        System.out.println("\t\tNice! I've marked this task as done:");
    }

    public static void displayGotIt() {
        System.out.println("\t\tGot it. I've added this task:");
    }

    public static void displayError_noItem() {
        System.out.println("\t\t☹ OOPS!!! There is no item.");
    }

    public static void displayError_dateFormat() {
        System.out.println("\t\tMake sure to enter date in format: dd-MMM-yy hh:mm PM/AM");
    }

    public static void displayTodoError() {
        System.out.println("\t\t☹ OOPS!!! The description of a todo cannot be empty.");
    }

    public static void displayDeadlineEventError() {
        System.out.println("\t\t☹ OOPS!!! The description of a deadline/event cannot be empty.");
    }

    public static void showLine() {
        System.out.println("\t________________________________________________________________");
    }

    /*public static void showError(String error_message) {
        System.out.println("\t☹ OOPS!!! " + error_message);
    }*/

    public static void goodBye() {
        System.out.println("\t\tBye. Hope to see you again soon!");
    }
}
