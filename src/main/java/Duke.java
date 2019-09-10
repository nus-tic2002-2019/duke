import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static Task[] store = new Task[100];
    public static int current = 0;

    public static void printItem(String[] items){
        for(int i = 0;i < current; i++){
            System.out.println(Integer.toString(i + 1) + ". " + items[i]);
        }
        System.out.printf("\n");
    }


    public static void checkString(String args){
        String line;
        Scanner in = new Scanner(System.in);
        if(args.equals("bye")){
            System.out.println("Bye. Hope to see you again soon!\n");
            return;
        } else if (args.equals("list")){
            printItem(store);
            line = in.nextLine();
            checkString(line);
        } else {
            Task[current] = new Task(args);
            System.out.println("Added: " + args + "\n");
            current += 1;
            line = in.nextLine();
            checkString(line);
        }
    }


    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String line;
        Scanner in = new Scanner(System.in);
        System.out.println("Hello I'm from\n" + logo + "What can I do for you?");
        line = in.nextLine();
        checkString(line);
    }
}
