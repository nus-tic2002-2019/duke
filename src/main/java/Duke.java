import java.util.ArrayList;
import java.util.Scanner;
import Tasks.*;
import Exceptions.*;


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



//
//        String [] a = new String[2];
//
//        a[0] = "a";
//
//        System.out.println(a.length);
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
                try {
                    if(tasklist.size() == 0){
                        throw new DukeException();
                    }
                }
                catch (Exception e){
                    printLines(line);
                    System.out.println("     ☹ OOPS!!! The list is empty.");
                    printLines(line);
                    break;
                }
                displayTaskList(userInput);
                break;

            case "done" :
                String tmp = "";
                int storeTaskNo = 0;
                if(userInput.length() == 4){
                    tmp = "";
                    storeTaskNo = 0;
                }
                else {
                    tmp = userInput.substring(userInput.indexOf("done") + 5, userInput.length()).trim();
                    if (!tmp.equals("")) {
                        storeTaskNo = Integer.parseInt(parse(userInput)[1]);
                    }
                }
                System.out.println(" WHAT IS THIS LA : " + tmp);
                //System.out.println("@@@@@ WHAT IS THE STIRNG : " + userInput.substring(userInput.indexOf("done")));
                System.out.println("CHECK THIS FKING VALUE : " + storeTaskNo);

                printLines(line);
                try {
                    if(userInput.substring(userInput.indexOf("done")).length() == 4
                    || userInput.substring(userInput.indexOf("done")+5, userInput.length()).trim().equals("")){
                        throw new DukeEmptyException("done");
                    }
                    if(tmp.equals("")){
                        throw new DukeEmptyException("done");
                    }
                    int t = Integer.parseInt((userInput.substring(userInput.indexOf("done")+5).trim()));
                   if(storeTaskNo > tasklist.size() || storeTaskNo > t || storeTaskNo == 0) {
                        throw new DukeOutOfBoundsException("done");
                   }
                   if(parse(userInput)[1].toString()==null){
                        throw new NumberFormatException();
                   }


                }
                catch (DukeEmptyException e){
                    System.out.println(e.getMessage());
                    //System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
                    printLines(line);
                    break;
                }
                catch (NumberFormatException e){
                    System.out.println("     ☹ OOPS!!! The task number must be a numerical value.");
                    printLines(line);
                    break;
                }
                catch (DukeOutOfBoundsException e){
                    System.out.println("     ☹ OOPS!!! The task number must be within range.");
                    printLines(line);
                    break;
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                    //System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
                    printLines(line);
                    break;
                }

                tasklist.get(storeTaskNo-1).markAsDone();
                tasklist.set(storeTaskNo-1, tasklist.get(storeTaskNo-1));

                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("      " + tasklist.get(storeTaskNo-1).getDescription());
                printLines(line);
                break;
            case "todo" :
                printLines(line);

                try {
                    if(userInput.substring(userInput.indexOf("todo")).length() == 4){
                        throw new DukeEmptyException("todo");
                    }
                    if((userInput.substring(userInput.indexOf("todo")+5, userInput.length())).trim().equals("")){
                        throw new DukeEmptyException("todo");
                    }
                }
                catch (DukeEmptyException e){
                    System.out.println(e.getMessage());
                    //System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
                    printLines(line);
                    break;
                }
                catch (NumberFormatException e){
                    System.out.println("     ☹ OOPS!!! The task number must be a numerical value.");
                    break;
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                    //System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
                    printLines(line);
                    break;
                }
                System.out.println("     Got it. I've added this task:");

                tasklist.add(new toDos(parse(userInput)[1]));
                //.set()
                System.out.println("       " + tasklist.get(tasklist.size()-1).getDescription());
                System.out.println("     Now you have " + tasklist.size() + " tasks in the list.");
                printLines(line);
                break;

            case "deadline" :
                try {
                    if(userInput.substring(userInput.indexOf("deadline")).length() == 8
                      || userInput.substring(userInput.indexOf("deadline")+9, userInput.length()).trim().equals("")){
                        throw new DukeEmptyException("deadline");
                    }

                }
                catch (DukeEmptyException e){
                    System.out.println(e.getMessage());
                    printLines(line);
                    break;
                }
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
                try {
                    if(userInput.substring(userInput.indexOf("event")).length() == 5 || (userInput.substring(userInput.indexOf("event")+5, userInput.length())).trim().equals("")){
                        throw new DukeEmptyException("event");
                    }

                }
                catch (DukeEmptyException e){
                    System.out.println(e.getMessage());
                    //System.out.println("     ☹ OOPS!!! The description of a event cannot be empty.");
                    printLines(line);
                    break;
                }
                catch (NumberFormatException e){
                    System.out.println("     ☹ OOPS!!! The task number must be a numerical value.");
                    break;
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                    //System.out.println("     ☹ OOPS!!! The description of a event cannot be empty.");
                    printLines(line);
                    break;
                }
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
                try {
                    if(userInput.length() == 0){
                        throw new DukeEmptyException("input");
                    }
                    else {
                        throw new DukeException();
                    }
//                    tasklist.add(new Task(userInput));
//                    System.out.println(userInput);
//                    printLines(line);
//                    System.out.println("     added: " + userInput);
//                    printLines(line);
//                    status = true;
                }
                catch (DukeEmptyException e){
                    System.out.println(e.getMessage());
                    //System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
                    printLines(line);
                    status = true;
                    break;
                }
                catch (DukeException e){
                    System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    printLines(line);
                    status = true;
                    break;
                }

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

//    public static String[] split(String input) throws Exception{
//        String[] tmp;
//        switch (input) {
//            case "done" :
//
//
//        }
//    }

    public static void main(String[] args) {
        Greet();
        //while(exit(echo()));
        while (echo());


    }
}
