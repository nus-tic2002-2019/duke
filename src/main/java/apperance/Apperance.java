package apperance;

import taskclasses.Task;

import java.util.Vector;

public class Apperance {
    Apperance(){}

    public static void Greeting() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void Separated_Line(){
        System.out.print("    ");
        for(int i=0; i<100; i++){
            System.out.print("-");
        }
        System.out.println("");
    }

    public static void Out_After_Delete(Task Deleted_Task, Vector<Task> List){
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       [" + Deleted_Task.getType() + "][" + Deleted_Task.getStatusIcon() + "] " + Deleted_Task.getDescription());
        System.out.println("     Now you have " + List.size() + " task(s) in the list.");
    }
}
