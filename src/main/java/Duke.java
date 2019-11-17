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

    private static final String MSG_GREET = "____________________________________________________________\n"
            + "Hello! I'm Duke\n"
            + "What can I do for you?\n"
            + "____________________________________________________________";

    private static final String MSG_BYE = "____________________________________________________________\n"
            + "Bye. Hope to see you again soon!\n"
            + "____________________________________________________________";

    private static final String MSG_PRE_TASK = "____________________________________________________________\n"
            + "Got it. I've added this task:";

    private static final String MSG_POST_TASK = "Now you have " + (tasks.size()) + " tasks in the list."
            + "\n____________________________________________________________";

    //Store keywords' number of characters
    private static int doneStrLen = STRING_DONE.length();
    private static int deleteStrLen = STRING_DELETE.length();
    private static int todoStrLen = STRING_TODO.length();
    private static int deadlineStrLen = STRING_DEADLINE.length();
    private static int eventStrLen = STRING_EVENT.length();

    //Declare error inputs in an array
    private static String[] errorInputList = {"blah"};

    public static void greetUser() {
        System.out.println(MSG_GREET);
    }

    public static void runApp() throws StringIndexOutOfBoundsException, DukeException {
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
            System.out.println("____________________________________________________________");
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.print(i + 1);
                System.out.print(".");
                System.out.println(tasks.get(i));
            }
            System.out.println("____________________________________________________________");

        } else if (input.length() >= doneStrLen && (input.substring(0, doneStrLen) ).equals(STRING_DONE) ) {
            //Mark task as done

            //Get task index
            String taskIndex = input.substring(doneStrLen + 1, input.length() );
            int taskNum = Integer.parseInt(taskIndex) - 1;

            //Set task as done
            tasks.get(taskNum).setDone();

            //Print completed task
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(taskNum));
            System.out.println("____________________________________________________________");

        } else if (input.length() >= deleteStrLen && (input.substring(0, deleteStrLen) ).equals(STRING_DELETE) ){
            //Delete task

            //Get task index
            String taskIndex = input.substring(deleteStrLen + 1, input.length());
            int taskNum = Integer.parseInt(taskIndex) - 1;

            //Reset task done status
            tasks.get(taskNum).resetDone();

            //Print task to be deleted
            System.out.println("____________________________________________________________");
            System.out.println("Noted. I've removed this task:");
            System.out.println(tasks.get(taskNum));
            System.out.println("____________________________________________________________");

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
            } else if (input.length() >= deadlineStrLen && (input.substring(0, deadlineStrLen) ).equals(STRING_DEADLINE) ) {
                //Add deadline
                inputExtract = input.substring(deadlineStrLen + 1, input.length() );
                tasks.add(new Deadline(inputExtract) );
            } else if (input.length() >= eventStrLen && (input.substring(0, eventStrLen) ).equals(STRING_EVENT) ) {
                //Add event
                inputExtract = input.substring(eventStrLen + 1, input.length() );
                tasks.add(new Event(inputExtract) );
            } else {
                //Add task
                inputExtract = input;
                tasks.add(new Task(inputExtract) );
            }

            System.out.println(MSG_PRE_TASK);
            System.out.println(tasks.get(tasks.size() -1) );
            System.out.println(MSG_POST_TASK);
        }

        //Recurring method
        runApp();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Bye!");

        //Declare constant variables
        final String MSG_INDEX_ERROR = "____________________________________________________________\n"
                +"OOPS!!! The description of a done/todo/deadline/event cannot be empty.\n"
                + "____________________________________________________________\n";

        final String MSG_DUKE_ERROR = "____________________________________________________________\n"
                +"OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + "____________________________________________________________\n";

        greetUser();

        try {
            runApp();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(MSG_INDEX_ERROR);
        } catch (DukeException e) {
            System.out.println(MSG_DUKE_ERROR);
        }
    }
}
