package ui;

import task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represent a class of user interface.
 * Display results and affirmation of user's command.
 */
public class Ui {
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
        System.out.println("\t--------------------------------------------------");
        System.out.println("\tPlease use the following format:");
        System.out.println("\ttodo task description");
        System.out.println("\tdeadline task description /by yyyy-mm-dd hhmm");
        System.out.println("\tevent description /at yyyy-mm-dd hhmm");
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
        String timeString = "";
        for (int i = 0; i < index ; i++) {
            String command = t.get(i).toString().substring(1,2);
            switch (command){
                case ("D"):
                    timeString = t.get(i).toString().split("by:")[1].replace(")","");
                    break;
                case ("E"):
                    timeString = t.get(i).toString().split("at:")[1].replace(")","");
                    break;
            }
            if (command.equals("T")) {
                System.out.println("\t" + (i + 1) + "." + t.get(i));
            } else {
                System.out.println("\t" + (i + 1) + "." + t.get(i).toString().replace(timeString,customDate(timeString)));
            }
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

    public static void singleList(ArrayList<Task> t, Integer index){
        String timeString = "";
        String command = t.get(index).toString().substring(1,2);
        switch (command){
            case ("D"):
                timeString = t.get(index).toString().split("by:")[1].replace(")","");
                break;
            case ("E"):
                timeString = t.get(index).toString().split("at:")[1].replace(")","");
                break;
        }
        if (command.equals("T")) {
            System.out.println("\t" + (index + 1) + "." + t.get(index));
        } else {
            System.out.println("\t" + (index + 1) + "." + t.get(index).toString().replace(timeString,customDate(timeString)));
        }

    }
}
