import javax.swing.plaf.synth.SynthTabbedPaneUI;
import java.util.InvalidPropertiesFormatException;
import java.util.Vector;
import java.util.Scanner;

public class Duke {

    public static boolean isNumeric(String strNum) {
        boolean ret = true;
        try {

            Double.parseDouble(strNum);

        }catch (NumberFormatException e) {
            ret = false;
        }
        return ret;
    }

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
            return (isDone ? "\u2713" : "\u2718");
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

    public static class Todo extends Task{
        public Todo(String description){
            super(description, "T");
        }
    }

    public static class Deadline extends Task{

        public Deadline(String description, String by){
            super(description, "D");
            Time = by;
        }
    }

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
        //List.remove(List.size()-1);
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
        System.out.println("     Got it。 I've added this task: ");
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

    //Chatting body;
    public static void chatting_Vector_Task(Vector<Task> List){
        String Ending = "bye";
        String Input;

        Scanner in = new Scanner(System.in);
        Input = in.nextLine();

        String In = new String(Input); //To store input into a String first;

        Separated_Line();

        String[] Input_Words = Input.split(" ");    //To split input by " " into String Array;
        String First_Word = Input_Words[0];     //To get the first word of input;



        //If the input is "bye";
        if(In.equals(Ending)){
            System.out.println("     Bye. Hope to see you again soon!");
            Separated_Line();
            return;
        }
        //If the input is "list";
        else if (In.equals("list")){
            Print_List(List);
            Separated_Line();
            chatting_Vector_Task(List);
        }
        //The rest;
        else{
            //If the input first four char is "done"
            if(First_Word.equals("done")){
                if(isNumeric(Input_Words[1]) && Input_Words.length == 2) {
                    int i = Integer.parseInt(Input_Words[1]) - 1;
                    List.get(i).isDone = true;
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.println("       [" + List.get(i).getStatusIcon() + "] " + List.get(i).getDescription());
                    Separated_Line();
                    chatting_Vector_Task(List);
                }
                else{
                    System.out.println("Invalid Task, please key in again!");
                    chatting_Vector_Task(List);
                }
            }
            else
            {
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
                else {
                    System.out.println("Invalid Input. Please input again!");
                    chatting_Vector_Task(List);
                }

                Out_After_Added(New_task, First_Word, List);
                Separated_Line();
                chatting_Vector_Task(List);
            }
        }
    }

    public static void main(String[] args) {
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

    //Chatting_Level 1&2;
    /*public static void chatting(){
        String Ending = "bye";
        String Line;
        Scanner in = new Scanner(System.in);
        Line = in.nextLine();
        Seperated_Line();

        if(Line.equals(Ending)){
            System.out.println("Bye. Hope to see you again soon!");
            Seperated_Line();
            return;
        }
        else {
            System.out.println(Line);
            Seperated_Line();
            chatting();
        }
    }*/

    //Chatting_vector_Task_Level 3;
    /*public static void chatting_Vector_Task(Vector<Task> List){
        String Ending = "bye";
        String Input;

        Scanner in = new Scanner(System.in);
        Input = in.nextLine();

        Task In = new Task(Input);

        List.add(In);

        Separated_Line();
        String last = List.lastElement().getDescription();  //To get the newest input from Task into String
        boolean status = List.lastElement().getStatus();    //To get the isDone status of the newest input


        //If the input is "bye";
        if(last.equals(Ending)){
            System.out.println("Bye. Hope to see you again soon!");
            Separated_Line();
            return;
        }
        //If the input is "list";
        else if (last.equals("list")){
            Print_List(List);
            Separated_Line();
            chatting_Vector_Task(List);
        }
        //The rest;
        else{
            //If the input first four char is "done"
            if(Checking_Input_Status(last)){
                String[] Last_Input = last.split(" "); //Split String base on " " into String Array;
                if(isNumeric(Last_Input[1]) && Last_Input.length == 2) {
                    int i = Integer.parseInt(Last_Input[1]) - 1;
                    List.get(i).isDone = true;
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  [" + List.get(i).getStatusIcon() + "] " + List.get(i).getDescription());
                    Separated_Line();
                    chatting_Vector_Task(List);
                }
            }
            else
                {
                    System.out.println("added: " + last);
                    Separated_Line();
                    chatting_Vector_Task(List);
                }
        }
    }*/
