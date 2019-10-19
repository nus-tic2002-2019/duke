package taskclasses;

import thrownexceptions.DeleteNumberException;
import thrownexceptions.DoneNumberException;
import ui.Ui;
import java.util.Vector;
import static taskclasses.InputInfor.*;

public class TaskList {

    //To check whether the string is a number;
    public static boolean isNumeric(String strNum) {
        boolean ret = true;
        try {
            Double.parseDouble(strNum);

        }catch (NumberFormatException e) {
            ret = false;
        }
        return ret;
    }

    //To add new Task into Vector list;
    public static void To_Add_New_Input_Into_List(String First_Word, String Input, String[] Input_Words, Vector<Task> List) {

        Task New_task = new Task();

        //if the input is Todo;
        if (First_Word.equals("todo")) {
            New_task = new Todo(Description(Input));
            List.add(New_task);
        }
        //if the input is Deadline;
        else if (First_Word.equals("deadline")) {
            New_task = new Deadline(Description(Input), Deadline_time(Input));
            List.add(New_task);
        }
        //if the input is Event;
        else if (First_Word.equals("event")) {
            New_task = new Event(Description(Input), Event_time(Input));
            List.add(New_task);
        }

        Ui.Out_After_Added(New_task, First_Word, List);
    }

    //To check Done Number input is in correct format or not and then decide whether proceed;
    public static void Done_Number(Vector<Task> List, String[] Input_Words) throws DoneNumberException {
        int DoneNumber = Integer.parseInt(Input_Words[1]);
        int L_size = List.size();

        if(!isNumeric(Input_Words[1]) || Input_Words.length != 2 || DoneNumber > L_size || DoneNumber < 1){
            throw new DoneNumberException();
        }

        int i = Integer.parseInt(Input_Words[1]) - 1;
        List.get(i).isDone = true;
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       [" + List.get(i).getStatusIcon() + "] " + List.get(i).getDescription());
    }

    //To check Delete Number input is in correct format or not and then decide whether proceed;
    public static void Delete_Number(Vector<Task> List, String[] Input_Words) throws DeleteNumberException {
        int DeleteNumber_Index = Integer.parseInt(Input_Words[1]);
        int L_size = List.size();

        if(!isNumeric(Input_Words[1]) || Input_Words.length != 2 || DeleteNumber_Index > L_size || DeleteNumber_Index < 1) {
            throw new DeleteNumberException();
        }

        Task Deleted_Task = List.remove(DeleteNumber_Index - 1);

        Ui.Out_After_Delete(Deleted_Task, List);
    }

    //To print Task list from Vector List;
    public static void Print_List(Vector<Task> List) {

        if(List.size() == 0){
            System.out.println("     There is no Task in the List.");
            return;
        }

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
