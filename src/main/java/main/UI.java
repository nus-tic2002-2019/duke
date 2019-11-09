package main;

import main.TaskLists.Task;

import java.util.ArrayList;

import static main.Duke.Tasks;


/*
*   User Interface Class
*   Displays results and processed user commands
* */
public class UI{
    UI(){
    }

    //TODO Create UI Methods for displaying duke Exception Messages

    public static void completeTask(int index) {
        UI.line();
        System.out.println("\tNice! I've marked this Task as Done:");
        System.out.println("\t" + Tasks.get(index));
        UI.line();
    }

     void welcome(){
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

    private static void line() {
        System.out.println("\t____________________________________________________________");
    }

    public static void bye(){
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }

    public static void echo(String input){
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
        System.out.println("\tHere are the Tasks in your List:");
        int count = 0;
        for (Object ignored : Tasks) {
            System.out.println( "\t" + (count + 1) + ". " +
                    Tasks.get(count ));
            count++;
        }
        System.out.println("\tNow you have " + count + " tasks in the list.");
        UI.line();
    }
}
