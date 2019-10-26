import ERROR_HANDLING.DukeException;
import ERROR_HANDLING.InvalidCommandException;
import TASK.Deadline;
import TASK.Event;
import TASK.Task;
import TASK.Todo;

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

//DEFINE Keyword
        HashMap<String, Command> keywords = new HashMap<String, Command>();

        String echo = " ";

//MAKE LIST
        ArrayList<Task> list = new ArrayList<Task>();

//Add new command TODO: make it a function?
        keywords.put("list", new Command() {
            public void run(String content) {
                cmdPrintList(list);
            };
        } );
        keywords.put("done", new Command() {
            public void run(String content) {
                cmdMarkDone(content, list);
            };
        } );
        keywords.put("todo", new Command() {
            public void run(String content) {
                cmdTodo(content, list);
            };
        } );
        keywords.put("deadline", new Command() {
            public void run(String content) {
                cmdDeadline(content, list);
            };
        } );
        keywords.put("event", new Command() {
            public void run(String content) {
                cmdEvent(content, list);
            };
        } );
        keywords.put("bye", new Command() {
            public void run(String content) {
                System.out.println("\tBye. Hope to see you again soon!");
            };
        } );



//USER INPUT

        while(echo != null) {
            echo = in.nextLine();
            echo = echo.toLowerCase();
            echo = echo.trim();

            String[] parts = echo.split(" ", 2);

            if (keywords.containsKey(parts[0])){
                keywords.get(parts[0]).run(echo);
            }

            /*
            try {

            } catch (DukeException e) {
                System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } */
        }


        System.out.println("\tBye. Hope to see you again soon!");

    }

    interface Command {
        //void run();
        void run(String content);
    }

    public static void cmdMarkDone(String echo, ArrayList<Task> list) {
        int listIndex = getIntStringSpace(echo) - 1;
        list.get(listIndex).setcompleted();
        printMarkDone(list, listIndex);
    }
    public static void cmdTodo(String echo, ArrayList<Task> list) {
        String content = removeKeyword(echo);
        list.add(new Todo(content));
        int index = list.get(0).getTotalTask() - 1;
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t  " + list.get(index));
        System.out.printf("\tNow you have %d tasks in the list."
                + System.lineSeparator(), list.get(0).getTotalTask());
    }
    public static void cmdDeadline(String echo, ArrayList<Task> list) {
        String content = removeKeyword(echo);
        String[] parts = content.split(" /by ");
        list.add(new Deadline(parts[0], parts[1]));
        int index = list.get(0).getTotalTask() - 1;
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t  " + list.get(index));
        System.out.printf("\tNow you have %d tasks in the list."
                + System.lineSeparator(), list.get(0).getTotalTask());
    }
    public static void cmdEvent(String echo, ArrayList<Task> list) {
        String content = removeKeyword(echo);
        String[] parts = content.split(" /at ");
        list.add(new Event(parts[0], parts[1]));
        int index = list.get(0).getTotalTask() - 1;
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t  " + list.get(index));
        System.out.printf("\tNow you have %d tasks in the list."
                + System.lineSeparator(), list.get(0).getTotalTask());
    }

    public static void printMarkDone(ArrayList<Task> list, int listIndex) {
        System.out.println("\tNice! I've marked this task as done: ");
        System.out.println("\t  " + list.get(listIndex));
    }

    /*
    public static boolean containsKeyword(String echo, HashMap keywords) throws InvalidCommandException {
        String[] parts = echo.split(" ", 2);

        if (keywords.containsKey(parts[0])){
            return true;
        }
        throw new InvalidCommandException();
    }

    public static String removeKeyword(String echo) {
        String[] parts = echo.split(" ", 2);
        return parts[1];
    }

     */

    public static int getIntStringSpace(String echo) {
        //echo = echo.substring(echo.indexOf(" ") + 1); //split number str
        echo = removeKeyword(echo);
        return Integer.parseInt(echo); // get number
    }

    public static void cmdPrintList(ArrayList<Task> list) {
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
