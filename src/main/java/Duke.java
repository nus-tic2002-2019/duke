import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");


        String line;
        Scanner input = new Scanner(System.in);
        while (true){
            line = input.nextLine();
            if(line.equals("list")){
                 System.out.println ("list");
            }
            if (line.equals("blah")){
                System.out.println ("blah");
            }
            if (line.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
        }
    }
}
