package ui;

import task.Task;
import java.util.ArrayList;

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
        for (int i = 0; i < index ; i++) {
            System.out.println("\t" + (i+1) + "." + t.get(i));
        }
        System.out.println("\t--------------------------------------------------");
    }
}
