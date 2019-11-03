import ERROR_HANDLING.*;
import TASK.*;
import COMMAND.*;

import java.io.IOException;
import java.util.*;
import STORAGE.*;
import UI.*;

public class Duke {
    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private Storage storage;
    private TempTaskList list;
    private Message ui;


    public Duke(){
        list = new TempTaskList();
        ui = new Message();

        try {
            storage = new Storage("data/taskSheet.txt");
        } catch (IOException ioe) {
            System.out.println("\tFile Error!");
        }

    };

    public void start() throws Exception {
        ui.showGreetingMessage();

        Scanner in = new Scanner(System.in);

// DEFINE Keyword
        HashMap<String, Command> keywords = new HashMap<String, Command>();
        // String[] keywords = {"done", "list", "deadline", "event"};

        String userInput = new String();

// MAKE LIST
        //ArrayList<Task> list = new ArrayList<Task>();

// Add new command TODO: make it a function?
        keywords.put("bye", new Command() {
            public void run(String content) {
                // Do absolutely nothing
            };
        } );

        keywords.put("list", new Command() {
            public void run(String content) {
                list.print();
            };
        } );
        keywords.put("done", new Command() {
            public void run(String content) throws Exception {
                cmdMarkDone(content, list);
            };
        } );
        keywords.put("todo", new Command() {
            public void run(String content) {
                try{
                    cmdTodo(content, list);
                } catch (DukeException e) {
                    System.out.println("\t☹ OOPS!!! The description of a todo cannot be empty.");
                }
            };
        } );
        keywords.put("deadline", new Command() {
            public void run(String content) {
                try {
                    cmdDeadline(content, list);
                } catch (DukeException e) {
                    System.out.println("\t☹ OOPS!!! The description of a deadline cannot be empty.");
                }
            };
        } );
        keywords.put("event", new Command() {
            public void run(String content) {
                try {
                    cmdEvent(content, list);
                } catch (DukeException e){
                    System.out.println("\t☹ OOPS!!! The description of a event cannot be empty.");
                }

            };
        } );

// End adding commands

// MAIN LOGIC
// USER INPUT

        while(!userInput.equals("bye")) {
            userInput = in.nextLine();

            String firstWord = splitKeyword(userInput)[0];
            // Some command is single word; Some command must have second part after space
            String content = null;
            if (splitKeyword(userInput).length != 1) {
                content = splitKeyword(userInput)[1];
            }

            try {
                if (!keywords.containsKey(firstWord)){
                    throw new InvalidCommandException();
                }
            } catch (DukeException ex) {
                System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                continue;
            }

            keywords.get(firstWord).run(content);

        }

        ui.showExitMessage();

    }

    public static void main(String[] args) throws Exception {
        new Duke().start();
    }


    public static String[] splitKeyword(String userInput) throws DukeException {
        //command: list

        userInput = userInput.toLowerCase();
        userInput = userInput.trim();
        String[] parts = userInput.split(" ", 2);
        parts[0] = parts[0].trim();
        if (parts.length != 1) {
            parts[1] = parts[1].trim();
        }

        return parts;
    }

    public static String removeKeyword(String userInput) {
        String[] parts = userInput.split(" ", 2);
        return parts[1];
    }


    public static int getIntStringSpace(String userInput) {
        //userInput = userInput.substring(userInput.indexOf(" ") + 1); //split number str
        userInput = removeKeyword(userInput);
        return Integer.parseInt(userInput); // get number
    }

    public static void cmdMarkDone(String content, TempTaskList list) throws Exception {
        try {
            int listIndex = Integer.parseInt(content) - 1;
            if (listIndex < 0 || listIndex > list.size()) {
                throw new IndexOutOfBoundsException();
            }
            list.get(listIndex).setcompleted();
            printMarkDone(list, listIndex);
        } catch (NumberFormatException e) {
            System.out.println("\t☹ OOPS!!! please input a Task Number instead ~");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t☹ OOPS!!! please input a valid Task No. ~");
        }
    }
    public static void cmdTodo(String content, TempTaskList list) throws NullContentException {
        //String content = removeKeyword(userInput);

        if (content == null) {
            throw new NullContentException();
        }

        list.set(new Todo(content));
        int index = list.get(0).getTotalTask() - 1;
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t  " + list.get(index));
        System.out.printf("\tNow you have %d tasks in the list."
                + System.lineSeparator(), list.get(0).getTotalTask());
    }

    public static void cmdDeadline(String content, TempTaskList list) throws NullContentException {
        //String content = removeKeyword(userInput);
        if (content == null) {
            throw new NullContentException();
        }
        String[] parts = content.split(" /by ");
        list.set(new Deadline(parts[0], parts[1]));
        int index = list.get(0).getTotalTask() - 1;
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t  " + list.get(index));
        System.out.printf("\tNow you have %d tasks in the list."
                + System.lineSeparator(), list.get(0).getTotalTask());
    }

    public static void cmdEvent(String content, TempTaskList list) throws NullContentException {
        //String content = removeKeyword(userInput);
        if (content == null) {
            throw new NullContentException();
        }
        String[] parts = content.split(" /at ");
        list.set(new Event(parts[0], parts[1]));
        int index = list.get(0).getTotalTask() - 1;
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t  " + list.get(index));
        System.out.printf("\tNow you have %d tasks in the list."
                + System.lineSeparator(), list.get(0).getTotalTask());
    }

    public static void printMarkDone(TempTaskList list, int listIndex) {
        System.out.println("\tNice! I've marked this task as done: ");
        System.out.println("\t  " + list.get(listIndex));
    }

    /*
    public static boolean containsKeyword(String userInput, HashMap keywords) throws InvalidCommandException {
        String[] parts = userInput.split(" ", 2);

        if (keywords.containsKey(parts[0])){
            return true;
        }
        throw new InvalidCommandException();
    }
     */

/*
    public static void cmdPrintList(ArrayList<Task> list) {
        System.out.println("\tHere are the tasks in your list: ");
        int taskNumber = 1;
        for (Task task : list) {
            System.out.printf("\t%d.%s" + System.lineSeparator(),taskNumber, task);
            ++taskNumber;
        }
    }
*/

    /*
    @Ovoutide
    public String toString() {
        return "|"  + logo + "|" ;
    } */



}
