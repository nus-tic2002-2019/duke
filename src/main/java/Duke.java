import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        String input;
        System.out.println("\n\t____________________________________________________________" 
        +"\n\tHello! I'm Duke\n\tWhat can I do for you?"
        + "\n\t____________________________________________________________");

        do {
            input = sc.next();
            switch(input){
                case "bye":
                    System.out.println("\n\t____________________________________________________________" + 
                    "\n\tBye. Hope to see you again soon!" +
                    "\n\t____________________________________________________________");
                    break;
                default:
                    System.out.println("\n\t____________________________________________________________\n\t" 
                    + input 
                    + "\n\t____________________________________________________________");
            }
        }
        while(!(input.equals("bye")));
    }
}