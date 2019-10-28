package ui;

import task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represent a class of user interface.
 * Display results and affirmation of user's command.
 */
public class Ui{
    public Ui(){
    }

    public void welcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\t--------------------------------------------------");
        System.out.println("\tHello! I'm duke.Duke\n\tWhat can I do for you?");
        System.out.println("\tType help for help-page.");
        System.out.println("\t--------------------------------------------------");
    }

    /**
     * This is an individual feature 2. It display a help page with all available
     * commands and description.
     *
     */
    public static void help(){
        System.out.println("\t Welcome to the help page!");
        System.out.println("\t Please use the following scheduler commands and format.");
        System.out.println("\t|===============================================================|");
        System.out.println("\t| Command  | Task Description  | Separator |      Date and time |");
        System.out.println("\t|===============================================================|");
        System.out.println("\t| deadline | eg. submit report |   /by     |    2019-12-31 2359 |");
        System.out.println("\t|          |                   |           | or 31-12-2019 2359 |");
        System.out.println("\t|---------------------------------------------------------------|");
        System.out.println("\t| event    | eg. watch movie   |   /at     |    2019-12-31 2359 |");
        System.out.println("\t|          |                   |           | or 31-12-2019 2359 |");
        System.out.println("\t|---------------------------------------------------------------|");
        System.out.println("\t| todo     | eg. buy bread     |          not applicable        |");
        System.out.println("\t|---------------------------------------------------------------|");
        System.out.println("\t| list     | example of a display list and its legend           |");
        System.out.println("\t|          | [E][X]watch movie(at:Nov 15 2019 04:30 PM)         |");
        System.out.println("\t|          | [E]: E,D and T denotes event, deadline and todo.   |");
        System.out.println("\t|          | [X]: X and √ denotes outstanding and completed task|");
        System.out.println("\t|---------------------------------------------------------------|");
        System.out.println("\t Example:");
        System.out.println("\t deadline submit industrial report /by 2019-12-31 2359");
        System.out.println("\t todo buy bread and butter");
        System.out.println("\t list\n");
        System.out.println("\t Additional helper commands.");
        System.out.println("\t|=============================================================|");
        System.out.println("\t| Command | Argument       | Description                      |");
        System.out.println("\t|=============================================================|");
        System.out.println("\t| done    | Task number    | to mark a task as completed      |");
        System.out.println("\t|-------------------------------------------------------------|");
        System.out.println("\t| delete  | Task number    | to delete a task                 |");
        System.out.println("\t|-------------------------------------------------------------|");
        System.out.println("\t| find    | keywords       | to find a keyword from task list |");
        System.out.println("\t|-------------------------------------------------------------|");
        System.out.println("\t| sort    | not applicable | sort task list by date           |");
        System.out.println("\t|-------------------------------------------------------------|");
        System.out.println("\t| help    | not applicable | display all commands and usage   |");
        System.out.println("\t|-------------------------------------------------------------|");
        System.out.println("\t Example:");
        System.out.println("\t find watch movie");
        System.out.println("\t sort");
        System.out.println("\t done 12\n");
    }

    public static void invalid(){
        System.out.println("\tOops!! You have key an invalid command.");
    }

    public static void bye(){
        System.out.println("\t--------------------------------------------------");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t--------------------------------------------------");
    }

    public static void added(ArrayList<Task> t, Integer index){
        System.out.println("\t--------------------------------------------------");
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + t.get(index-1)); //t[taskNo-1])
        System.out.println("\tNow you have " + index + " tasks in the list");
        System.out.println("\t--------------------------------------------------");
    }

    public static void done(ArrayList<Task> t, Integer index){
        System.out.println("\t--------------------------------------------------");
        System.out.println("\tNice! I marked this task as done");
        System.out.println("\t" + t.get(index-1));
        System.out.println("\t--------------------------------------------------");
    }

    public static void delete(ArrayList<Task> t, Integer index){
        System.out.println("\t--------------------------------------------------");
        System.out.println("\tNoted! I have removed this task.");
        System.out.println("\t" + t.get(index-1));
        System.out.println("\tNow you have " + (t.size()-1) + " tasks in the list");
        System.out.println("\t--------------------------------------------------");
    }

    public static void list(ArrayList<Task> t, Integer index){
        System.out.println("\t--------------------------------------------------");
        System.out.println("\tHere are the tasks in your list:");
        for ( int i = 0; i < index ; i++ ) {
            singleList(t, i);
        }
        System.out.println("\t--------------------------------------------------");
    }

    /**
     * This method display date from format yyyy-mm-dd hhmm to MMM d yyyy hh:mm a
     * @param timeString : date string of format yyyy-mm-dd hhmm
     * @return date string of format MMM d yyyy hh:mm a
     */
    public static String customDate(String timeString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        LocalDateTime dateString = LocalDateTime.parse(timeString);
        return dateString.format(formatter);
    }

    /**
     * This method will display a single line of task.
     * @param t : this is the data structure of user tasks
     * @param index : user's required task serial number.
     */
    public static void singleList(ArrayList<Task> t, Integer index) {
        String timeString = "";
        String command = t.get(index).toString().substring(1, 2);
        switch (command) {
            case ("D"):
                timeString = t.get(index).toString().split("by:")[1].replace(")", "");
                break;
            case ("E"):
                timeString = t.get(index).toString().split("at:")[1].replace(")", "");
                break;
        }
        if (command.equals("T")) {
            System.out.println("\t"+(index+1)+"."+t.get(index));
        } else {
            System.out.println("\t"+(index+1)+"."+t.get(index).
                                                  toString().
                                                  replace(timeString, customDate(timeString)));
        }
    }
}
