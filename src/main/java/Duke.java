import java.util.ArrayList;
import java.util.Scanner;
import Tasks.*;


public class Duke {
    static final String line = "    ____________________________________________________________";
   // public static ArrayList<String> list = new ArrayList<>();  // ArrayList to store userInputs

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




        String [] a = new String[2];

        a[0] = "a";

        System.out.println(a.length);
    }

    public static boolean echo(){

        String userInput;  // To Store userinput
        Scanner scan = new Scanner( System.in ); // To getting userinput
        userInput = scan.nextLine();

        // Select parsed String
        String parsed = parse(userInput)[0].toLowerCase();
        boolean status = true;
        int lastIdx = tasklist.size() - 1;
        switch (parsed) {
            case "list" :
                displayTaskList(userInput);
                break;

            case "done" :
                String tmp = parse(userInput)[1].toString();
                int tmpp = Integer.parseInt(parse(userInput)[1]);
                tasklist.get(tmpp-1).markAsDone();
                tasklist.set(tmpp-1, tasklist.get(tmpp-1));
                printLines(line);
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("      " + tasklist.get(tmpp-1).getDescription());
                break;
            case "todo" :
                printLines(line);
                System.out.println("     Got it. I've added this task:");
               // tasklist.get(tasklist.size()) = new toDos(userInput, true);
                tasklist.add(new toDos(parse(userInput)[1]));
                //.set()
                System.out.println("       " + tasklist.get(tasklist.size()-1).getDescription());
                System.out.println("     Now you have " + tasklist.size() + " tasks in the list.");
                printLines(line);
                break;

            case "deadline" :
                String des = userInput.substring(userInput.indexOf("deadline")+9, userInput.indexOf("by")-1);
                String by = userInput.substring(userInput.indexOf("by")+3, userInput.length());
                System.out.println(by);
                printLines(line);
                System.out.println("     Got it. I've added this task:");
                tasklist.add(new Deadlines(des, by));
//                System.out.println("       " + tasklist.get(tasklist.size()-1).getDescription());
                System.out.println("       " + tasklist.get(tasklist.size()-1).toString());
                System.out.println("     Now you have " + tasklist.size() + " tasks in the list.");
                printLines(line);
                break;

            case "event" :
                printLines(line);
                System.out.println("      Got it. I've added this task:");
                tasklist.add(new Events(parse(userInput)[1]));
                System.out.println("       " + tasklist.get(tasklist.size()-1).getDescription());
                System.out.println("     Now you have " + tasklist.size() + " tasks in the list.");
                printLines(line);
                break;


            case "bye" :
                System.out.println(userInput);
                printLines(line);
                System.out.println("     Bye. Hope to see you again soon!");
                printLines(line);
                status = false;
                break;

            default:
                tasklist.add(new Task(userInput));
                System.out.println(userInput);
                printLines(line);
                System.out.println("     added: " + userInput);
                printLines(line);
                status = true;
        }

        return status;
    }

    /*
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


     */
    //Displaying Task class list
    public static void displayTaskList(String input){
        System.out.println(input);
        printLines(line);
        System.out.println("     Here are the tasks in your list:");
        for(int i = 0; i < tasklist.size(); i++){
            int idx = i+1;
            System.out.println("     "+ idx + ". " + tasklist.get(i).toString() );
        }
        printLines(line);
    }

    public static String[] parse(String input){
        String[] act = input.split(" ",2);
        return act;
    }

    public static void main(String[] args) {
        Greet();
        //while(exit(echo()));
        while (echo());


    }
}
