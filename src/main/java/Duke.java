import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static Task[] store = new Task[100];
    public static int current = 0;

    public static void printItem(Task[] items){
        for(int i = 0;i < current; i++){
            System.out.println(Integer.toString(i + 1) + ".[" + items[i].getStatusIcon() + "] " + items[i].description);
        }
        System.out.printf("\n");
    }

    public static void listeningEvents(){
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        checkString(line);
    }


    public static void checkString(String args){
        String[] inputs = args.split(" ");
        switch(inputs[0]) {
            case "done":
                int index =  Integer.parseInt(inputs[1]) - 1;
                store[index].setIsDone();
                System.out.println("Nice! I've marked this task as done: \n" + "[" + store[index].getStatusIcon()
                        + "] " + store[index].description);
                System.out.printf("\n");
                listeningEvents();
                break;
            case "bye":
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            case "list":
                printItem(store);
                listeningEvents();
                break;
            default:
                store[current] = new Task(args);
                System.out.println("Added: " + args + "\n");
                current += 1;
                listeningEvents();
        }


    }


    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello I'm from\n" + logo + "What can I do for you?");
        listeningEvents();
    }
}
