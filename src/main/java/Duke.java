import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static final String line = "    ____________________________________________________________";
    public static ArrayList<String> list = new ArrayList<>();  // ArrayList to store userInputs
    //public static int idx = 0;
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
        String userInput;
        Scanner scan = new Scanner( System.in );
        userInput = scan.nextLine();
//        System.out.println(userInput);
//        printLines(line);
//        System.out.println("     " + userInput);
//        printLines(line);
        if(!userInput.equals("list"))
        list.add(userInput);

        return userInput;
    }

    public static boolean exit(String userInput){
        if(userInput.equals("list")){
            displayList(userInput);
        }
        if(userInput.equals("bye")){
            System.out.println(userInput);
            printLines(line);
            //System.out.println("     " + userInput);
            System.out.println("     Bye. Hope to see you again soon!");
            printLines(line);
            return false;
        }
        else if(!userInput.equals("list")){
            System.out.println(userInput);
            printLines(line);
            System.out.println("     added: " + userInput);
            printLines(line);
        }
        return true;
    }
    public static void displayList(String input){
        int idx = 1;
        if(input.equals("list")){
//            for(String a:list)
            System.out.println(input);
            printLines(line);
            for(int i = 0; i < list.size(); i++){
                System.out.println(idx + ". " +  list.get(i));
                idx++;
            }
            printLines(line);

        }
    }

    public static void main(String[] args) {
        Greet();
        while(exit(echo()));
        //displayList(list);

    }
}
