package ui;

import parser.Parser;
import taskclasses.Task;
import taskclasses.TaskList;
import thrownexceptions.*;
import java.util.Scanner;
import java.util.Vector;
import static taskclasses.TaskList.*;

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

    /**
     * To get the searching type from user
     * @return return the type user chose;
     */
    public static String Searching_Type_Choice(){
        System.out.println("Search Mode. Please choose the index the type for searching:\n" +
                "1. Date\n" +
                "2. Time\n" +
                "3. Description\n" +
                "4. Task Type");

        String search_type;
        Scanner search_IN = new Scanner(System.in);
        search_type = search_IN.nextLine().toLowerCase();

        if(!search_type.equals("1") && !search_type.equals("2") && !search_type.equals("3") && !search_type.equals("4")) {
            System.out.println("The index you chose is not correct. Please try again.");
            search_type = Searching_Type_Choice();
        }

        return search_type;
    }

    /**
     * To get the List type from user
     * @return return the type user chose;
     */
    public static String Search_Task_List_Choice(){
        System.out.println("Search Mode. Please choose the index the Task List for searching:\n" +
                "1. Task List\n" +
                "2. ToDoAfter List\n" +
                "3. Both of above");

        String List_Type;
        Scanner type = new Scanner(System.in);
        List_Type = type.nextLine().toLowerCase();

        if(!List_Type.equals("1") && !List_Type.equals("2") && !List_Type.equals("3")) {
            System.out.println("The index you chose is not correct. Please try again.");
            List_Type = Search_Task_List_Choice();
        }

        return List_Type;
    }

    /**
     * The inter-action UI with user
     * @param List Task List
     */
    public static void chatting_Vector_Task(Vector<Task> List, Vector<Task> ToDoAfterList, String Date_Type, String Time_Type) throws InputDateTimeTooEarly {
        String Ending = "bye";
        String Input;

        try {

            Scanner in = new Scanner(System.in);
            Input = in.nextLine();

            Ui.Separated_Line();

            String[] Input_Words = Input.split(" ");
            String First_Word = Parser.Input_Type(Input);

            Parser.Input_Length_Checking(First_Word, Input_Words);

            switch (First_Word){
                case "todo":
                case "event":
                case "deadline":
                    TaskList.To_Add_New_Input_Into_List(First_Word, Input, Input_Words, List);
                    break;
                case "done":
                    TaskList.Done_Number(List, Input_Words);
                    break;
                case "delete":
                    TaskList.Delete_Number(List, Input_Words);
                    break;
                case "list":
                    TaskList.Print_List(List, Date_Type, Time_Type);
                    break;
                case "bye":
                    System.out.println("     Bye. Hope to see you again soon!");
                    Ui.Separated_Line();
                    return;
                case "search":
                    Search(Date_Type, Time_Type, List, ToDoAfterList);
                    break;
                case "ToDoAfter":
                    ToDoAfter_Task(ToDoAfterList, List);
                    break;
                default:
                    System.out.println("    Invalid Input! Please try again!");
            }
        }
        catch (DukeException e) {
            String First_Word = DukeException.getFirst_Word();

            switch (First_Word) {
                case "todo":
                    System.out.println("     \u2639" + " OOPS!!! The description of a todo cannot be empty.");
                    break;
                case "event":
                    System.out.println("     \u2639" + " OOPS!!! The description of a event cannot be empty.");
                    break;
                case "deadline":
                    System.out.println("     \u2639" + " OOPS!!! The description of a deadline cannot be empty.");
                    break;
                default:
                    System.out.println("     \u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
            }
        }
        catch (DoneNumberException e){
            System.out.println("      The task you want to done is invalid! Please key-in again!");
        }
        catch (DeleteNumberException e){
            System.out.println("     The number of task you want to delete is invalid! Please key-in again!");
        }
        catch (InputTimeBeforeLocal e) {
            System.out.println("     The input time cannot earlier then local time.");
        }
        catch (DateTimeInputFormatWrongly e){
            System.out.println("     The input Datetime format wrongly. Please try again.");
        }
        catch (SearchTypeWrong e) {
            System.out.println("     The search type wrongly. Please try again.");
        }
        catch (DateTimeInputWrongly e) {
            System.out.println("    The date or time input wrongly. Please try again.");
        }
        catch (MonthIndexWrong monthIndexWrong) {
            System.out.println("    The month input is not correct. Please try again.");
        }
        catch (EnumDayIndexWrongly enumDayIndexWrongly) {
            System.out.println("The day input is not correct. Please try again.");
        }

        Ui.Separated_Line();
        chatting_Vector_Task(List, ToDoAfterList, Date_Type, Time_Type);
    }
}
