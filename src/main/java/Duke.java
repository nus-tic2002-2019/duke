import javax.swing.plaf.synth.SynthScrollBarUI;
import javax.swing.plaf.synth.SynthTabbedPaneUI;
import java.util.Arrays;
import java.math.RoundingMode;
import java.awt.*;
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

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        //system("CLS");

        Seperated_Line();
        Greeting();
        Seperated_Line();

        chatting();
    }
}
