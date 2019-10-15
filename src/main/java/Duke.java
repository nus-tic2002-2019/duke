import actions.Input_Action;
import apperance.Apperance;
import taskclasses.Task;
import java.util.Scanner;
import java.util.Vector;


public class Duke {
    //num list;
    public static enum First_Input_Word{
        todo("todo", 1),
        event("event", 2),
        deadline("deadline", 3),
        done("done", 4),
        delete("delete", 5),
        list("list", 6),
        bye("bye", 7);

        private  String name;
        private int index;

        private First_Input_Word(String name, int index){
            this.name = name;
            this.index = index;
        }

        public String getName(){
            return this.name;
        }
    }

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

    //DukeException class which extends Exception;
    public static class DukeException extends Exception {}

    //DoneNumberException;
    public static class DoneNumberException extends Exception{}

    //DeleteNumberException;
    public static class DeleteNumberException extends Exception{}

    //To check input with enum list;
    public static class InputNotMatchEnum extends Exception{}

    //To check the length of input;
    public static void Input_Length_Checking(String First_Word, String[] Input_Words) throws Duke.DukeException{
        if(!First_Word.equals("list") && !First_Word.equals("bye") && Input_Words.length == 1){
            throw new Duke.DukeException();
        }
    }

    //To check Done Number input is in correct format or not;
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

    //To check Delete Number input is in correct format or not;
    public static void Delete_Number(Vector<Task> List, String[] Input_Words) throws DeleteNumberException{
        int DeleteNumber_Index = Integer.parseInt(Input_Words[1]);
        int L_size = List.size();

        if(!isNumeric(Input_Words[1]) || Input_Words.length != 2 || DeleteNumber_Index > L_size || DeleteNumber_Index < 1) {
              throw new DeleteNumberException();
        }

        Task Deleted_Task = List.remove(DeleteNumber_Index - 1);

        Apperance.Out_After_Delete(Deleted_Task, List);
    }

    //To compare input with enum list;
    public static String matchName(String n) throws InputNotMatchEnum {
        for( First_Input_Word testing : First_Input_Word.values()){
            if(testing.getName().equals(n)) return testing.getName();
        }

        throw new InputNotMatchEnum();
    }

    //Chatting body;
    public static void chatting_Vector_Task(Vector<Task> List) throws DukeException {
        String Ending = "bye";
        String Input;

        Scanner in = new Scanner(System.in);
        Input = in.nextLine();

        String In = new String(Input); //To store input into a String first;

        Apperance.Separated_Line();

        String[] Input_Words = Input.split(" ");    //To split input by " " into String Array;
        String First_Word = Input_Words[0];     //To get the first word of input;


        try {
            Input_Length_Checking(First_Word, Input_Words);

            String Compared_Result = matchName(First_Word);

            switch (First_Word){
                case "todo":
                case "event":
                case "project":
                    Input_Action.To_Add_New_Input_Into_List(First_Word, Input, Input_Words, List);
                    break;
                case "done":
                    Done_Number(List, Input_Words);
                    break;
                case "delete":
                    Delete_Number(List, Input_Words);
                    break;
                case "list":
                    Input_Action.Print_List(List);
                    break;
                case "bye":
                    System.out.println("     Bye. Hope to see you again soon!");
                    Apperance.Separated_Line();
                    return;
                default:
                    System.out.println("    Invalid Input! Please try again!");
            }
        }
        catch (DukeException e) {
            if (First_Word.equals("todo")) {
                System.out.println("     \u2639" + " OOPS!!! The description of a todo cannot be empty.");
            } else if (First_Word.equals("event")) {
                System.out.println("     \u2639" + " OOPS!!! The description of a event cannot be empty.");
            } else if (First_Word.equals("deadline")) {
                System.out.println("     \u2639" + " OOPS!!! The description of a deadline cannot be empty.");
            } else {
                System.out.println("     \u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        catch (DoneNumberException e){
            System.out.println("      The task you want to done is invalid! Please key-in again!");
        }
        catch (DeleteNumberException e){
            System.out.println("     The number of task you want to delete is invalid! Please key-in again!");
        }
        catch (InputNotMatchEnum e){
            System.out.println("    Input not valid, please try again!");
        }

        Apperance.Separated_Line();
        chatting_Vector_Task(List);
    }

    //Main;
    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Vector<Task> List = new Vector<>();

        Apperance.Separated_Line();
        Apperance.Greeting();
        Apperance.Separated_Line();

        chatting_Vector_Task(List);
    }
}