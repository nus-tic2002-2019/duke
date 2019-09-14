import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<String> list = new ArrayList<>();
    public static ArrayList<Boolean> mark = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        while(printout(echo()));
    }

      public static String echo(){
        String userInput;
        Scanner scan = new Scanner( System.in );
        userInput = scan.nextLine();

         if(userInput.contains("done")) {
             String[] splitStr = userInput.split("\\s+");
             // System.out.println("ddd " + splitStr[1]);
             mark.set(Integer.valueOf(splitStr[1])-1,  true);
        }
         else if(!userInput.equals("list")) {
             list.add(userInput);
             mark.add(false);
         }
        return userInput;
    }

    public static boolean printout(String userInput){
        if(userInput.equals("bye")){
            System.out.println("Bye. Hope to see you again soon!");
            return false;
        }
        else if(userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");

                for (int i = 0; i < list.size(); i++)
                {
                    System.out.println(i+1 + ". [" + getMark(mark.get(i))+ "] " + list.get(i));
                }
        }
        else if(userInput.contains("done")) {
           // int index = Integer.valueOf(userInput.substring(userInput.length() + 1)) - 1;
            String[] splitStr = userInput.split("\\s+");
            int index = Integer.valueOf(splitStr[1])-1;
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println("[" + getMark(mark.get(index))+ "] " + list.get(index));

        }
        else {
            System.out.println("Added: " + userInput);
        }
        return true;
    }

    public static String getMark(boolean result) {

        if(result == true)
            return "✓";
        else
             return "X";
    }
}
