import javax.swing.plaf.synth.SynthTabbedPaneUI;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.InvalidPropertiesFormatException;
import java.util.Vector;
import java.util.Scanner;


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

    //Task class;
    public static class Task{
        protected String description;
        protected String type;
        protected String Time;
        protected boolean isDone = false;

        public Task(){}

        public Task(String description, String type){
            this.description = description;
            this.type = type;
            isDone = false;
        }

        public String getStatusIcon(){
            return (isDone ? String.valueOf('\u2713') : "\u2718");
        }

        public String getDescription(){
            return description;
        }

        public String getType(){ return type; }

        public String getTime(){
            return Time;
        }

        public boolean getStatus() {
            return isDone;
        }
    }

    //To do class;
    public static class Todo extends Task{
        public Todo(String description){
            super(description, "T");
        }
    }

    //Deadline class;
    public static class Deadline extends Task{

        public Deadline(String description, String by){
            super(description, "D");
            Time = by;
        }
    }

    //Event class;
    public static class Event extends Task {

        public Event(String description, String Event_time){
            super(description, "E");
            Time = Event_time;
        }
    }

    //Greeting at beginning;
    public static void Greeting (){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    //To print the separated line;
    public static void Separated_Line(){
        System.out.print("    ");
        for(int i=0; i<100; i++){
            System.out.print("-");
        }
        System.out.println("");
    }

    //To print everything in the List;
    public static void Print_List(Vector<Task> List){

        System.out.println("     Here are the task(s) in your list:");

        for(int i = 0; i < List.size(); i++){
            int j = i+1;
            Task current = List.get(i);
            String StatusIcon = current.getStatusIcon();
            String TypeIcon = current.getType();
            System.out.print("     " + j + ".[" + TypeIcon + "][" + StatusIcon + "] " + current.getDescription());
            if(TypeIcon.equals("D")){
                System.out.print(" (by: " + current.getTime() + ")");
            }
            else if(TypeIcon.equals("E")) {
                System.out.print(" (at: " + current.getTime() + ")");
            }
            System.out.println();
        }
    }

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

    //To get starting time;
    public static String Deadline_time(String Input){
        String ST;

        int ST_index = Input.lastIndexOf("by") + 3;

        ST = Input.substring(ST_index);

        return ST;
    }

    //To get ending time;
    public static String Event_time(String Input){
        String ET;

        int ET_index = Input.lastIndexOf("at");

        if(ET_index == -1){
            ET_index = Input.lastIndexOf("on") + 3;
        }
        else {
            ET_index = ET_index + 3;
        }

        ET = Input.substring(ET_index);

        return ET;
    }

    //To get description of the input;
    public static String Description(String Input){
        String D;
        String[] body = Input.split(" ");

        int D_index = Input.indexOf(" ") + 1;

        if(body[0].equals("todo")){
            D = Input.substring(D_index);
        }
        else{

            int slash_index = Input.length();

            if(body[0].equals("deadline")){
                slash_index = Input.lastIndexOf("by");
            }
            else {
                slash_index = Input.lastIndexOf("at");
            }

            D = Input.substring(D_index, slash_index - 1).trim();
        }

        return D.trim();
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

    //To check and delete;
    public static void Out_After_Delete(Task Deleted_Task, Vector<Task> List){
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       [" + Deleted_Task.getType() + "][" + Deleted_Task.getStatusIcon() + "] " + Deleted_Task.getDescription());
        System.out.println("     Now you have " + List.size() + " task(s) in the list.");
    }

    //DukeException class which extends Exception;
    public static class DukeException extends Exception {}

    //DoneNumberException;
    public static class DoneNumberException extends Exception{}

    //DeleteNumberException;
    public static class DeleteNumberException extends Exception{}

    //To check input with enum list;
    public static class InputNotMatchEnum extends Exception{}

    //To check input information length;
    public static void Input_Length_Checking(String First_Word, String[] Input_Words) throws DukeException {
        if(!First_Word.equals("list") && !First_Word.equals("bye") && Input_Words.length == 1){
            throw new DukeException();
        }
    }

    //To add input into the List;
    public static void To_Add_New_Input_Into_List(String First_Word, String Input, String[] Input_Words, Vector<Task> List){

        Task New_task = new Task();

        //if the input is Todo;
        if(First_Word.equals("todo")){
            New_task = new Todo(Description(Input));
            List.add(New_task);
        }
        //if the input is Deadline;
        else if(First_Word.equals("deadline")){
            New_task = new Deadline(Description(Input), Deadline_time(Input));
            List.add(New_task);
        }
        //if the input is Event;
        else if(First_Word.equals("event")){
            New_task = new Event(Description(Input), Event_time(Input));
            List.add(New_task);
        }

        Out_After_Added(New_task, First_Word, List);
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

        Out_After_Delete(Deleted_Task, List);
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

        Separated_Line();

        String[] Input_Words = Input.split(" ");    //To split input by " " into String Array;
        String First_Word = Input_Words[0];     //To get the first word of input;


        try {
            Input_Length_Checking(First_Word, Input_Words);

            String Compared_Result = matchName(First_Word);

            switch (First_Word){
                case "todo":
                case "event":
                case "project":
                    To_Add_New_Input_Into_List(First_Word, Input, Input_Words, List);
                    break;
                case "done":
                    Done_Number(List, Input_Words);
                    break;
                case "delete":
                    Delete_Number(List, Input_Words);
                    break;
                case "list":
                    Print_List(List);
                    break;
                case "bye":
                    System.out.println("     Bye. Hope to see you again soon!");
                    Separated_Line();
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

        Separated_Line();
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

        Separated_Line();
        Greeting();
        Separated_Line();

        chatting_Vector_Task(List);
    }
}