import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static Task[] store = new Task[100];
    public static int current = 0;
    public static Scanner in = new Scanner(System.in);

    public static void printItem(Task[] items){
        for(int i = 0;i < current; i++){
            System.out.println(Integer.toString(i + 1) + ".[" + items[i].getStatusIcon() + "] " + items[i].description);
        }
        System.out.printf("\n");
    }

    public static void listeningEvents(){
        String line;
        line = in.nextLine();
        checkString(line);
    }


    public static void checkString(String args){
        String[] inputs = args.split(" ");
        int index_date;
        switch(inputs[0]) {
            case "event":
                index_date = args.indexOf("/by");
                store[current] = new Event(args.substring(6, index_date) + "(by: " + args.substring(index_date + 3) + ")");
                System.out.println("Got it. I've added this task: \n");
                current += 1;
                listeningEvents();
            case "deadline":
                index_date = args.indexOf("/by");
                store[current] = new Deadline(args.substring(8, index_date) + "(by: " + args.substring(index_date + 3) + ")");
                System.out.println("Got it. I've added this task: \n");
                current += 1;
                listeningEvents();
            case "todo":
                store[current] = new Todo(args.substring(5));
                System.out.println("Got it. I've added this task: \n");
                current += 1;
                listeningEvents();
            case "done":
                int index =  Integer.parseInt(inputs[1]) - 1;
                store[index].setIsDone();
                System.out.println("Nice!! I've marked this task as done: \n" + "[" + store[index].getStatusIcon() + "] " + store[index].description);
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
