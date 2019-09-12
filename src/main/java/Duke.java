import java.util.ArrayList;
import java.util.Scanner;
import Tasks.*;

public class Duke {
    static final String line = "    ____________________________________________________________";
    public static ArrayList<String> list = new ArrayList<>();  // ArrayList to store userInputs

    //Changing to using Task type
    public static ArrayList<Task> tasklist = new ArrayList<>();
    //public static int idx = 0;

    //public static Task actions = new Task("");

    public static void printLines(String k){
        System.out.println(k);
    }

    public static void Greet(){
//        String line = ;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLines(line);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        printLines(line);
    }

    public static String echo(){

        String userInput;  // To Store userinput
        Scanner scan = new Scanner( System.in ); // To getting userinput
        userInput = scan.nextLine();

        if(!userInput.equals("list") && !parse(userInput)[0].equals("done") && !userInput.toLowerCase().equals("bye"))
            tasklist.add(new Task(userInput));


        if(!userInput.equals("list") && !userInput.contains("done") && !userInput.toLowerCase().equals("bye")){
            System.out.println(userInput);
            printLines(line);
            System.out.println("     added: " + userInput);
            printLines(line);
        }

        if(parse(userInput)[0].equals("done")){
            String tmp = parse(userInput)[1].toString();
            int tmpp = Integer.parseInt(parse(userInput)[1]);
            //talist.set(tmpp-1, tmp);
            tasklist.get(tmpp-1).markAsDone();
            tasklist.set(tmpp-1, tasklist.get(tmpp-1));
            printLines(line);
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("      " + tasklist.get(tmpp-1).getDescription());
        }

        return userInput;
    }

    public static boolean exit(String userInput){
        if(userInput.equals("list")){
            displayTaskList(userInput);
        }
        else if(userInput.toLowerCase().equals("bye")){
            System.out.println(userInput);
            printLines(line);
            System.out.println("     Bye. Hope to see you again soon!");
            printLines(line);
            return false;
        }
        return true;
    }

    //Displaying Task class list
    public static void displayTaskList(String input){
        System.out.println(input);
        printLines(line);
        System.out.println("     Here are the tasks in your list:");
        for(int i = 0; i < tasklist.size(); i++){
            int idx = i+1;
            System.out.println("     "+ idx + ". " + tasklist.get(i).getDescription() );
        }
        printLines(line);
    }

    public static String[] parse(String input){
        String[] act = input.split(" ",2);
        return act;
    }

    public static void main(String[] args) {
        Greet();
        while(exit(echo()));
        //displayList(list);

    }
}
