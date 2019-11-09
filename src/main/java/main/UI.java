package main;

import java.util.ArrayList;

import static main.Duke.Tasks;


/*
*   User Interface Class
*   Displays results and processed user commands
* */
public class UI {
    UI(){
    }

    public static void completeTask(int index) {
        UI.line();
        System.out.println("\tNice! I've marked this Task as Done:");
        System.out.println("\t[" +
                        Tasks.get(index).getStatusIcon() + "] " +
                        Tasks.get(index));
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

    public static void addedCommand(String input) {
        UI.line();
        System.out.println("Added: " + input);
        UI.line();
    }

    //TODO Update listTask to print Type before Status
    public static void listTasks() {
        UI.line();
        System.out.println("\tHere are the Tasks in your List:");
        int count = 1;
        for (Object ignored : Tasks) {
            System.out.println( "\t" + count + ".[" +
                    Tasks.get(count -1).getStatusIcon() + "] " +
                    Tasks.get(count - 1));
            count++;
        }
        UI.line();
    }
}
