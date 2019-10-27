package ui;

import taskclasses.Task;

import java.util.Vector;

public class Ui {

    /**
     * The LoginFace to show
     */
    public static void LoginFace(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * The greeting with some instruction
     */
    public static void Greeting() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("**Date and time input format: /by(at): **");
        System.out.println("**1. Deadline: YYYY-MM-DD Hour(0-23):Minute(0-59)**");
        System.out.println("**2. YYYY-MM-DD Hour(0-23):Minute(0-59) -> Hour(0-23):Minutes(0-59)**");
        System.out.println("**3. YYYY-MM-DD Hour(0-23):Minute(0-59) -> YYYY-MM-DD Hour(0-23):Minute(0-59)**");
    }

    /**
     * Sperated_Line
     */
    public static void Separated_Line(){
        System.out.print("    ");
        for(int i=0; i<100; i++){
            System.out.print("-");
        }
        System.out.println("");
    }

    /**
     * The output after new task added
     * @param New_Task New_Task going to add into Vector Task List
     * @param First_Word The first word which contain task type
     * @param List Vector<Task> List
     */
    public static void Out_After_Added(Task New_Task, String First_Word, Vector<Task> List) {
        System.out.println("     Got it. I've added this task: ");
        System.out.print("       [" + New_Task.getType() + "][" + New_Task.getStatusIcon() + "] " + New_Task.getDescription());

        if (First_Word.equals("deadline")) {
            System.out.print(" (by: " + New_Task.getDateTime() + ")");

        } else if (First_Word.equals("event")) {
            System.out.print(" (at: " + New_Task.getDateTime() + ")");
        }

        System.out.println();
        System.out.println("     Now you have " + List.size() + " task(s) in the list.");
    }

    /**
     * The output after new task deleted
     * @param Deleted_Task New_Task going to delete from Vector Task List
     * @param List Vector<Task> List
     */
    public static void Out_After_Delete(Task Deleted_Task, Vector<Task> List){
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       [" + Deleted_Task.getType() + "][" + Deleted_Task.getStatusIcon() + "] " + Deleted_Task.getDescription());
        System.out.println("     Now you have " + List.size() + " task(s) in the list.");
    }
}
