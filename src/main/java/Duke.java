import java.util.*;

public class Duke {

    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        Duke d = new Duke();
        //System.out.println(d);

        System.out.println("\tHello! I'm Duke \n\tWhat can I do for you?");

        Scanner in = new Scanner(System.in);

        String echo = " ";
        /* while(echo != null) {
            echo = in.nextLine();
            if (echo.equals("bye")) // DO NOT use ==
                break;
            System.out.println("\t" + echo + "\n");
        } */

//MAKE LIST
        ArrayList<Task> list = new ArrayList<Task>();

//USER INPUT
        while(echo != null) {
            echo = in.nextLine();
            echo = echo.trim();

            if (echo.equals("list")) {
                printList(list);
                continue;
            }

            if (echo.contains("done")) {
                echo = echo.substring(echo.indexOf(" ") + 1);
                int listIndex = Integer.parseInt(echo) - 1;
                list.get(listIndex).setcompleted();

                System.out.println("\tNice! I've marked this task as done: ");
                System.out.println("\t  " + list.get(listIndex));

                continue;
            }

            /////////
            if (echo.equals("bye"))
                break;
            list.add(new Task(echo));
        }

        System.out.println("\tBye. Hope to see you again soon!");

    }

    public static void printList(ArrayList<Task> list) {
        System.out.println("\tHere are the tasks in your list: ");
        int taskNumber = 1;
        for (Task task : list) {
            System.out.printf("\t%d.%s" + System.lineSeparator(),taskNumber, task);
            ++taskNumber;
        }
    }

    /*
    @Override
    public String toString() {
        return "|"  + logo + "|" ;
    } */



}
