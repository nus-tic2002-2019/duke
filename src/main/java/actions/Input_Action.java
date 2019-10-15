package actions;

import taskclasses.Deadline;
import taskclasses.Event;
import taskclasses.Task;
import taskclasses.Todo;

import java.util.Vector;

public class Input_Action {

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

    public static void To_Add_New_Input_Into_List(String First_Word, String Input, String[] Input_Words, Vector<Task> List) {

        Task New_task = new Task();

        //if the input is Todo;
        if (First_Word.equals("todo")) {
            New_task = new Todo(Task.Description(Input));
            List.add(New_task);
        }
        //if the input is Deadline;
        else if (First_Word.equals("deadline")) {
            New_task = new Deadline(Task.Description(Input), Task.Deadline_time(Input));
            List.add(New_task);
        }
        //if the input is Event;
        else if (First_Word.equals("event")) {
            New_task = new Event(Task.Description(Input), Task.Event_time(Input));
            List.add(New_task);
        }

        Out_After_Added(New_task, First_Word, List);
    }

    public static void Print_List(Vector<Task> List) {

        System.out.println("     Here are the task(s) in your list:");

        for (int i = 0; i < List.size(); i++) {
            int j = i + 1;
            Task current = List.get(i);
            String StatusIcon = current.getStatusIcon();
            String TypeIcon = current.getType();
            System.out.print("     " + j + ".[" + TypeIcon + "][" + StatusIcon + "] " + current.getDescription());
            if (TypeIcon.equals("D")) {
                System.out.print(" (by: " + current.getTime() + ")");
            } else if (TypeIcon.equals("E")) {
                System.out.print(" (at: " + current.getTime() + ")");
            }
            System.out.println();
        }
    }
}
