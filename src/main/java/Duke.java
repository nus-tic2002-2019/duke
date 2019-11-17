import java.util.Scanner; //Import Java Scanner
import java.util.ArrayList; //Import Java ArrayList

public class Duke {
    //Create task ArrayList
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    //Declare variables for keywords
    private static String byeString = "bye";
    private static String listString = "list";
    private static String doneString = "done";
    private static String deleteString = "delete";
    private static String todoString = "todo";
    private static String deadlineString = "deadline";
    private static String eventString = "event";

    //Store keywords' number of characters
    private static int doneStrLen = doneString.length();
    private static int deleteStrLen = deleteString.length();
    private static int todoStrLen = todoString.length();
    private static int deadlineStrLen = deadlineString.length();
    private static int eventStrLen = eventString.length();

    //Declare error inputs in an array
    private static String[] errorInputList = {"blah"};

    //Declare string to print
    private static String greetString = "____________________________________________________________\n"
            + "Hello! I'm Duke\n"
            + "What can I do for you?\n"
            + "____________________________________________________________";

    private static String bye = "____________________________________________________________\n"
            + "Bye. Hope to see you again soon!\n"
            + "____________________________________________________________";

    private static String preTaskMsg = "____________________________________________________________\n"
            + "Got it. I've added this task:";

    private static String postTaskMsg = "Now you have " + (tasks.size()) + " tasks in the list."
            + "\n____________________________________________________________";

    public static void greetUser() {
        System.out.println(greetString);
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

        if (input.equals(byeString) ) {
            //Bye
            System.out.println(bye);
            return;

        } else if (input.equals(listString) ) {
            //List tasks
            System.out.println("____________________________________________________________");
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.print(i + 1);
                System.out.print(".");
                System.out.println(tasks.get(i));
            }
            System.out.println("____________________________________________________________");

        } else if (input.length() >= doneStrLen && (input.substring(0, doneStrLen) ).equals(doneString) ) {
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

        } else if (input.length() >= deleteStrLen && (input.substring(0, deleteStrLen) ).equals(deleteString) ){
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

            if (input.length() >= todoStrLen && (input.substring(0, todoStrLen) ).equals(todoString) ) {
                //Add to-do

                //Exception of to-do without title
                /*if (todoStrLen + 1 > input.length() ) {
                    throw new StringIndexOutOfBoundsException();
                }*/

                inputExtract = input.substring(todoStrLen + 1, input.length() );
                tasks.add(new Todo(inputExtract) );
            } else if (input.length() >= deadlineStrLen && (input.substring(0, deadlineStrLen) ).equals(deadlineString) ) {
                //Add deadline
                inputExtract = input.substring(deadlineStrLen + 1, input.length() );
                tasks.add(new Deadline(inputExtract) );
            } else if (input.length() >= eventStrLen && (input.substring(0, eventStrLen) ).equals(eventString) ) {
                //Add event
                inputExtract = input.substring(eventStrLen + 1, input.length() );
                tasks.add(new Event(inputExtract) );
            } else {
                //Add task
                inputExtract = input;
                tasks.add(new Task(inputExtract) );
            }

            System.out.println(preTaskMsg);
            System.out.println(tasks.get(tasks.size() -1) );
            System.out.println(postTaskMsg);
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

        //Declare string to print
        String indexErrorMessage = "____________________________________________________________\n"
                +"OOPS!!! The description of a done/todo/deadline/event cannot be empty.\n"
                + "____________________________________________________________\n";

        String dukeErrorMessage = "____________________________________________________________\n"
                +"OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + "____________________________________________________________\n";

        greetUser();

        try {
            runApp();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(indexErrorMessage);
        } catch (DukeException e) {
            System.out.println(dukeErrorMessage);
        }
    }
}
