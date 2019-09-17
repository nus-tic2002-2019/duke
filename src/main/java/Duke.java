import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static Task[] store = new Task[100];
    public static int current = 0;
    public static Scanner in = new Scanner(System.in);

    public static void printItem(Task[] items){
        System.out.println("    ____________________________________________________________\n" +
                "     Here are the tasks in your list:");
        for(int i = 0;i < current; i++){
            System.out.println("    " + Integer.toString(i + 1) + "." + items[i].toString());
        }
        System.out.printf("    ____________________________________________________________\n");
    }

    public static void listeningEvents(){
        String line;
        line = in.nextLine();
        try{
            checkString(line);
            listeningEvents();
        } catch (StringIndexOutOfBoundsException e){
            System.out.println("Did you forget '/by'? Please try again!");
            listeningEvents();
        } catch (IllegalStringException e){
            System.out.println("Invalid command, Please Try Again!\n");
            listeningEvents();
        }
    }


    public static void checkString(String args) throws StringIndexOutOfBoundsException, IllegalStringException{
        String[] inputs = args.split(" ");
        int index_date;
        switch(inputs[0]) {
            case "event":
                index_date = args.indexOf("/by");
                store[current] = new Event(args.substring(6, index_date) + "(by:" + args.substring(index_date + 3) + ")");
                break;
            case "deadline":
                index_date = args.indexOf("/by");
                store[current] = new Deadline(args.substring(9, index_date) + "(by:" + args.substring(index_date + 3) + ")");
                break;
            case "todo":
                store[current] = new Todo(args.substring(5));
                break;
            case "done":
                int index =  Integer.parseInt(inputs[1]) - 1;
                store[index].setIsDone();
                break;
            case "bye":
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            case "list":
                printItem(store);
                break;
            default:
                throw new IllegalStringException();
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
