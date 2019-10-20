package ui;

import taskclasses.Task;

import java.util.Vector;

public class Ui {

    public static void LoginFace(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void Greeting() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("(Date and time input format: /by(at): YYYY-MM-DD Hour(0-23):Minute(0-59)");
    }

    public static void Separated_Line(){
        System.out.print("    ");
        for(int i=0; i<100; i++){
            System.out.print("-");
        }
        System.out.println("");
    }

    public static void Out_After_Added(Task New_Task, String First_Word, Vector<Task> List) {
        System.out.println("     Got it. I've added this task: ");
        System.out.print("       [" + New_Task.getType() + "][" + New_Task.getStatusIcon() + "] " + New_Task.getDescription());

        if (First_Word.equals("deadline")) {
            System.out.print(" (by: " + New_Task.getTime() + ")");

        } else if (First_Word.equals("event")) {
            System.out.print(" (at: " + New_Task.getTime() + ")");
        }

        System.out.println();
        System.out.println("     Now you have " + List.size() + " task(s) in the list.");
    }

    public static void Out_After_Delete(Task Deleted_Task, Vector<Task> List){
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       [" + Deleted_Task.getType() + "][" + Deleted_Task.getStatusIcon() + "] " + Deleted_Task.getDescription());
        System.out.println("     Now you have " + List.size() + " task(s) in the list.");
    }
}
