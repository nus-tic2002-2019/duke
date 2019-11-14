package main;

import main.taskLists.Task;

import java.util.Set;

import static main.Duke.Tasks;


/*
 *   User Interface Class
 *   Displays results and processed user commands
 * */
public class UI {
    UI() {
    }

    //TODO Create UI Methods for displaying duke Exception Messages

    public static void completeTask(int index) {
        UI.line();
        System.out.println("\tNice! I've marked this Task as Done:");
        System.out.println("\t" + Tasks.get(index));
        UI.line();
    }

    // TODO Refactor Count later on
    public static void deletedCommand(Task t) {
        UI.line();
        System.out.println("\tNoted. I've removed this Task:");
        System.out.println("\t" + t.toString());
        int count = 0;
        for (Object ignored : Tasks) {
            count++;
        }
        System.out.println("\tNow you have " + count + " tasks in the list.");
        UI.line();
    }

    private static void line() {
        System.out.println("\t____________________________________________________________");
    }

    public static void bye() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }

    public static void echo(String input) {
        UI.line();
        System.out.println("\t " + input);
        UI.line();
    }

    public static void addedCommand(Task input) {
        UI.line();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + input.toString());
        UI.line();
    }

    public static void listTasks() {
        UI.line();
        int count = 0;
        if (Tasks.size() > 0) {
            System.out.println("\tHere are the Tasks in your List:");
            for (Object input : Tasks) {
                System.out.println("\t" + (count + 1) + ". " +
                        input.toString());
                count++;
            }
            System.out.println("\tNow you have " + count + " tasks in the list.");
        } else {
            System.out.println("\tOh, you don't seem to be very busy huh?");
        }

        UI.line();
    }

    public static void listTasks(Set<Task> seached_set) {
        UI.line();
        if (seached_set.size() > 0) {
            System.out.println("\tHere are the matching tasks in your list:");
            int count = 1;
            for (Task element : seached_set) {
                System.out.println("\t" + (count + 1) + ". " +
                        element.toString());
                count++;
            }
        } else {
            System.out.println("\tOh, Nothing matches that description.");
        }

        UI.line();
    }

    public static void clear() {
        UI.line();
        System.out.println("\tI've cleared all your tasks!");
        UI.line();
    }

    public static void archived(String s) {
        UI.line();
        System.out.println("\tCurrent Tasks have been saved to '" + s + "'");
        UI.line();
    }

    /**
     * Prints the Welcome Logo of Duke
     */
    void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");

        System.out.println("\t____________________________________________________________");
    }
}
