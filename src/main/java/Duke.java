import javax.swing.plaf.synth.SynthScrollBarUI;
import javax.swing.plaf.synth.SynthTabbedPaneUI;
import java.util.Arrays;
import java.math.RoundingMode;
import java.awt.*;
import java.util.*;
import java.util.Vector;
import java.util.Scanner;

public class Duke {

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

    public static void  Print_List(Vector<String> List){
        List.remove(List.size()-1);
        for(int i = 0; i < List.size(); i++){
            int j = i+1;
            System.out.println(j + ". " + List.get(i));
        }
    }

    public static void chatting_Vector(Vector<String> List){
        String Ending = "bye";
        //Vector<String> List = new Vector<String>();
        Scanner in = new Scanner(System.in);
        List.add(in.nextLine());

        Seperated_Line();
        String last = List.lastElement();

        if(last.equals(Ending)){
            System.out.println("Bye. Hope to see you again soon!");
            Seperated_Line();
            return;
        }
        else if (last.equals("list")){
            Print_List(List);
            Seperated_Line();
            chatting_Vector(List);
        }
        else{
            System.out.println("added: " + last);
            Seperated_Line();
            chatting_Vector(List);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Vector<String> List = new Vector<String>();

        Seperated_Line();
        Greeting();
        Seperated_Line();

        chatting_Vector(List);
    }
}
