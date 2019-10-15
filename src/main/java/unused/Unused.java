package unused;

import taskclasses.Task;

import java.util.Vector;

public class Unused {

    Unused(){}

    //To check whether the first word is "done";
    public static boolean Checking_Input_Status(String Input) {
        if(Input.length() < 6) return false;

        String DoneString =Input.substring(0, 4);
        if(DoneString.equals("done")){
            return true;
        }
        else return false;
    }

    //To identify whether the first word is "Deadline", "Todo", "Event or something else;
    public static String Identify_T_D_E(String Input){
        String[] words = Input.split(" ");

        if(words[0].equals("Deadline")) return "D";
        if(words[0].equals("Todo")) return "T";
        if(words[0].equals("Event")) return "E";

        return "Invalid Input";
    }

    //Print staff after adding new task;
    public static void Out_After_Added(Task New_Task, String First_Word, Vector<Task> List){
        System.out.println("     Got it. I've added this task: ");
        System.out.print("       [" + New_Task.getType() + "][" + New_Task.getStatusIcon() + "] " + New_Task.getDescription());

        if(First_Word.equals("deadline")){
            System.out.print(" (by: " + New_Task.getTime() + ")");

        }
        else if(First_Word.equals("event")) {
            System.out.print(" (at: " + New_Task.getTime() + ")");
        }

        System.out.println();
        System.out.println("     Now you have " + List.size() + " task(s) in the list.");
    }
}
