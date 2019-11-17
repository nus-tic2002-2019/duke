import java.io.IOException;
import java.util.Scanner; //Import Java Scanner
import java.util.ArrayList; //Import Java ArrayList

public class Duke {
    //Create task ArrayList
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    //Declare constant variables
    private static final String STRING_BYE = "bye";
    private static final String STRING_LIST = "list";
    private static final String STRING_DONE = "done";
    private static final String STRING_DELETE = "delete";
    private static final String STRING_TODO = "todo";
    private static final String STRING_DEADLINE = "deadline";
    private static final String STRING_EVENT = "event";
    private static final String LINE_SEPARATOR = "____________________________________________________________\n";
    private static final String MSG_GREET = LINE_SEPARATOR + "Hello! I'm Duke\n" + "What can I do for you?\n" + LINE_SEPARATOR;
    private static final String MSG_BYE = LINE_SEPARATOR + "Bye. Hope to see you again soon!\n" + LINE_SEPARATOR;
    private static final String MSG_LIST = LINE_SEPARATOR + "Here are the tasks in your list:";
    private static final String MSG_DONE = LINE_SEPARATOR + "Nice! I've marked this task as done:";
    private static final String MSG_DELETE = LINE_SEPARATOR + "Noted. I've removed this task:";
    private static final String MSG_PRE_TASK = LINE_SEPARATOR + "Got it. I've added this task:";
    private static final String MSG_POST_TASK_1 = "Now you have ";
    private static final String MSG_POST_TASK_2 = " tasks in the list.\n" + LINE_SEPARATOR;
    private static final String ERROR_MSG_IO = "Input or output failure!\n";

    //Store keywords' number of characters
    private static int doneStrLen = STRING_DONE.length();
    private static int deleteStrLen = STRING_DELETE.length();
    private static int todoStrLen = STRING_TODO.length();
    private static int deadlineStrLen = STRING_DEADLINE.length();
    private static int eventStrLen = STRING_EVENT.length();

    //Declare error inputs in an array
    private static String[] errorInputList = {"blah"};

    public static void greetUser() {
        System.out.print(MSG_GREET);
    }

    public static void appendTaskToFile(Storage appStorage, String filePath, Task currentTask, String errorMessage) {
        try {
            appStorage.appendToFile(filePath, currentTask.printToFile() );
        } catch (IOException e) {
            System.out.println(errorMessage);
        }
    }

    public static void runApp(Storage appStorage, String filePath) throws StringIndexOutOfBoundsException, DukeException {
        //Get user input
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        //Check if input is error
        for (String s : errorInputList) {
            if (s.equals(input) ) {
                throw new DukeException();
            }
        }

        if (input.equals(STRING_BYE) ) {
            //Bye
            System.out.println(MSG_BYE);
            return;

        } else if (input.equals(STRING_LIST) ) {
            //List tasks
            System.out.println(MSG_LIST);
            for (int i = 0; i < tasks.size(); i++) {
                System.out.print(i + 1);
                System.out.print(".");
                System.out.println(tasks.get(i));
            }
            System.out.print(LINE_SEPARATOR);

        } else if (input.length() >= doneStrLen && (input.substring(0, doneStrLen) ).equals(STRING_DONE) ) {
            //Mark task as done

            //Get task index
            String taskIndex = input.substring(doneStrLen + 1, input.length() );
            int taskNum = Integer.parseInt(taskIndex) - 1;

            //Set task as done
            tasks.get(taskNum).setDone();

            //Print completed task
            System.out.println(MSG_DONE);
            System.out.println(tasks.get(taskNum));
            System.out.print(LINE_SEPARATOR);

        } else if (input.length() >= deleteStrLen && (input.substring(0, deleteStrLen) ).equals(STRING_DELETE) ){
            //Delete task

            //Get task index
            String taskIndex = input.substring(deleteStrLen + 1, input.length());
            int taskNum = Integer.parseInt(taskIndex) - 1;

            //Reset task done status
            tasks.get(taskNum).resetDone();

            //Print task to be deleted
            System.out.println(MSG_DELETE);
            System.out.println(tasks.get(taskNum));
            System.out.print(LINE_SEPARATOR);

            //Delete task
            tasks.remove(taskNum);
        } else {
            String inputExtract;

            if (input.length() >= todoStrLen && (input.substring(0, todoStrLen) ).equals(STRING_TODO) ) {
                //Add to-do

                //Exception of to-do without title
                /*if (todoStrLen + 1 > input.length() ) {
                    throw new StringIndexOutOfBoundsException();
                }*/

                inputExtract = input.substring(todoStrLen + 1, input.length() );
                tasks.add(new Todo(inputExtract) );
                appendTaskToFile(appStorage, filePath, tasks.get(tasks.size() -1), ERROR_MSG_IO);
            } else if (input.length() >= deadlineStrLen && (input.substring(0, deadlineStrLen) ).equals(STRING_DEADLINE) ) {
                //Add deadline
                inputExtract = input.substring(deadlineStrLen + 1, input.length() );
                tasks.add(new Deadline(inputExtract) );
                appendTaskToFile(appStorage, filePath, tasks.get(tasks.size() -1), ERROR_MSG_IO);
            } else if (input.length() >= eventStrLen && (input.substring(0, eventStrLen) ).equals(STRING_EVENT) ) {
                //Add event
                inputExtract = input.substring(eventStrLen + 1, input.length() );
                tasks.add(new Event(inputExtract) );
                appendTaskToFile(appStorage, filePath, tasks.get(tasks.size() -1), ERROR_MSG_IO);
            } else {
                //Add task
                inputExtract = input;
                tasks.add(new Task(inputExtract) );
                appendTaskToFile(appStorage, filePath, tasks.get(tasks.size() -1), ERROR_MSG_IO);
            }

            System.out.println(MSG_PRE_TASK);
            System.out.println(tasks.get(tasks.size() -1) );
            System.out.print(MSG_POST_TASK_1);
            System.out.print(tasks.size() );
            System.out.print(MSG_POST_TASK_2);
        }

        //Recurring method
        runApp(appStorage, filePath);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        //System.out.println("Bye!");

        //Declare constant variables
        final String STORAGE_PATH_DUKE = "src/main/data/duke.txt";
        final String ERROR_MSG_INDEX = LINE_SEPARATOR +"OOPS!!! The description of a done/todo/deadline/event cannot be empty.\n" + LINE_SEPARATOR;
        final String ERROR_MSG_DUKE = LINE_SEPARATOR +"OOPS!!! I'm sorry, but I don't know what that means :-(\n" + LINE_SEPARATOR;

        //New storage
        Storage dukeStorage = new Storage(STORAGE_PATH_DUKE);

        //Test read file
        dukeStorage.readFile(STORAGE_PATH_DUKE);

        greetUser();

        try {
            runApp(dukeStorage, STORAGE_PATH_DUKE);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(ERROR_MSG_INDEX);
        } catch (DukeException e) {
            System.out.println(ERROR_MSG_DUKE);
        }
    }
}
