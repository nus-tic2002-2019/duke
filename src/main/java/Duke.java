import javax.swing.plaf.synth.SynthScrollBarUI;
import javax.swing.plaf.synth.SynthTabbedPaneUI;
import java.util.Arrays;
import java.math.RoundingMode;
import java.awt.*;
import java.util.*;
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
        protected boolean isDone;

        public Task(String description){
            this.description = description;
            isDone = false;
        }

        public String getStatusIcon(){
            return (isDone ? "\u2713" : "\u2718");
        }

        public String getDescription(){
            return description;
        }

        public boolean getStatus() {
            return isDone;
        }
    }

    public static void Greeting (){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void Seperated_Line(){
        for(int i=0; i<100; i++){
            System.out.print("-");
        }
        System.out.println("");
    }

    public static void chatting(){
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
    }

    public static void Print_List(Vector<Task> List){
        List.remove(List.size()-1);
        for(int i = 0; i < List.size(); i++){
            int j = i+1;
            String StatusIcon = List.get(i).getStatusIcon();
            System.out.println(j + ".[" + StatusIcon + "] " + List.get(i).getDescription());
        }
    }

    public static boolean Checking_Input_Status(String Input){
        if(Input.length() < 6) return false;

        String DoneString =Input.substring(0, 4);
        if(DoneString.equals("done")){
            return true;
        }
        else return false;
    }

    public static void chatting_Vector_Task(Vector<Task> List){
        String Ending = "bye";
        String Input;

        Scanner in = new Scanner(System.in);
        Input = in.nextLine();

        Task In = new Task(Input);

        List.add(In);

        Seperated_Line();
        String last = List.lastElement().getDescription();  //To get the newest input from Task into String
        boolean status = List.lastElement().getStatus();    //To get the isDone status of the newest input


        //If the input is "bye";
        if(last.equals(Ending)){
            System.out.println("Bye. Hope to see you again soon!");
            Seperated_Line();
            return;
        }
        //If the input is "list";
        else if (last.equals("list")){
            Print_List(List);
            Seperated_Line();
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
                    Seperated_Line();
                    chatting_Vector_Task(List);
                }
            }
            else
                {
                    System.out.println("added: " + last);
                    Seperated_Line();
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

        Seperated_Line();
        Greeting();
        Seperated_Line();

        chatting_Vector_Task(List);
    }
}


//    public static void chatting_Vector(Vector<String> List){
//        String Ending = "bye";
//        //Vector<String> List = new Vector<String>();
//        Scanner in = new Scanner(System.in);
//        List.add(in.nextLine());
//
//        Seperated_Line();
//        String last = List.lastElement();
//
//        if(last.equals(Ending)){
//            System.out.println("Bye. Hope to see you again soon!");
//            Seperated_Line();
//            return;
//        }
//        else if (last.equals("list")){
//            Print_List(List);
//            Seperated_Line();
//            chatting_Vector(List);
//        }
//        else{
//            System.out.println("added: " + last);
//            Seperated_Line();
//            chatting_Vector(List);
//        }
//    }