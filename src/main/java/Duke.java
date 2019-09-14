import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        while(exit(echo()));
    }

    public static String echo(){
        String userInput;
        Scanner scan = new Scanner( System.in );
        userInput = scan.nextLine();

        if(!userInput.equals("list")) {
            list.add(userInput);
        }
        return userInput;
    }

    public static boolean exit(String userInput){
        if(userInput.equals("bye")){
            System.out.println("Bye. Hope to see you again soon!");
            return false;
        }
        else if(userInput.equals("list")) {
            for (int i = 0; i < list.size(); i++)
            {
                System.out.println(i+1 + ". " + list.get(i) + "\n");
            }
        }
        else {
            System.out.println("Added: " + userInput);
        }
        return true;
    }
}
