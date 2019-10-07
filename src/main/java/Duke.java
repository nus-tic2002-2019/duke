import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task> store = new ArrayList<>();
    public static Scanner in = new Scanner(System.in);

    public static void printItem(ArrayList<Task> items){
        System.out.println("    ____________________________________________________________\n" +
                "     Here are the tasks in your list:");
        for(int i = 0;i < store.size(); i++){
            System.out.println("    " + Integer.toString(i + 1) + "." + items.get(i).toString());
        }
        System.out.printf("    ____________________________________________________________\n");
    }

    public static void listeningEvents() throws ArrayIndexOutOfBoundsException {
        String line;
        line = in.nextLine();
        try{
            checkString(line);
        } catch (StringIndexOutOfBoundsException e){
            System.out.println("Did you forget '/by'? Please try again!");
            listeningEvents();
        } catch (IllegalStringException e){
            System.out.println("Invalid command, Please Try Again!\n");
            listeningEvents();
        }
    }

    public static void delete(int index){
        Task removed_item = store.remove(index);
        System.out.println("    ____________________________________________________________\n" +
                "     Noted. I've removed this task: \n" +
                "          " + removed_item.toString() + "\n" +
                "     Now you have " + store.size()  + " tasks in the list.\n" +
                "    ____________________________________________________________");
    }


    public static void checkString(String args) throws StringIndexOutOfBoundsException, IllegalStringException{
        String[] inputs = args.split(" ");
        int index_date;
        int index;
        switch(inputs[0]) {
            case "event":
                index_date = args.indexOf("/by");
                store.add(new Event(args.substring(6, index_date) + "(by:" + args.substring(index_date + 3) + ")"));
                listeningEvents();
                break;
            case "deadline":
                index_date = args.indexOf("/by");
                store.add(new Deadline(args.substring(9, index_date) + "(by:" + args.substring(index_date + 3) + ")"));
                listeningEvents();
                break;
            case "todo":
                store.add(new Todo(args.substring(5)));
                listeningEvents();
                break;
            case "done":
                index =  Integer.parseInt(inputs[1]) - 1;
                store.get(index).setIsDone();
                listeningEvents();
                break;
            case "bye":
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            case "list":
                printItem(store);
                listeningEvents();
                break;
            case "delete":
                index =  Integer.parseInt(inputs[1]) - 1;
                delete(index);
                listeningEvents();
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
        try{
            listeningEvents();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid Command!");
        }
    }
}
