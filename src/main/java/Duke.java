import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int tasksCounter = 0;

    public static void greet() {
        String greet = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________";

        System.out.println(greet);
    }

    public static void modify() throws StringIndexOutOfBoundsException, DukeException {
        //Declare variables for keywords
        String keyBye = "bye";
        String keyList = "list";
        String keyDone = "done";
        String keyTodo = "todo";
        String keyDeadline = "deadline";
        String keyEvent = "event";

        //Store keywords' number of characters
        int numDone = keyDone.length();
        int numTodo = keyTodo.length();
        int numDeadline = keyDeadline.length();
        int numEvent = keyEvent.length();

        //Declare error inputs in an array
        String[] errorInputs = {"blah"};

        //Get user input
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        //Check if input is error
        for (String s : errorInputs) {
            if (s.equals(input) ) {
                throw new DukeException();
            }
        }

        if (input.equals(keyBye) ) { //Bye
            String bye = "____________________________________________________________\n"
                    + "Bye. Hope to see you again soon!\n"
                    + "____________________________________________________________";
            System.out.println(bye);
            return;
        } else if (input.equals(keyList) ) { //List tasks
            System.out.println("____________________________________________________________");
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasksCounter; i++) {
                System.out.print(i + 1);
                System.out.print(".");
                System.out.println(tasks[i]);
            }
            System.out.println("____________________________________________________________");
        } else if (input.length() >= numDone && (input.substring(0, numDone) ).equals(keyDone) ) { //Mark task as done
            //Get task index
            String taskIndex = input.substring(numDone + 1, input.length() );
            int taskNum = Integer.parseInt(taskIndex) - 1;

            //Set task as done
            tasks[taskNum].setDone();

            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done:");

            //Print completed task
            System.out.println(tasks[taskNum]);

            System.out.println("____________________________________________________________");
        } else {
            String extractInput;

            if (input.length() >= numTodo && (input.substring(0, numTodo) ).equals(keyTodo) ) { //Add to-do
                //Exception of to-do without title
                /*if (numTodo + 1 > input.length() ) {
                    throw new StringIndexOutOfBoundsException();
                }*/

                extractInput = input.substring(numTodo + 1, input.length() );
                tasks[tasksCounter] = new Todo(extractInput);
            } else if (input.length() >= numDeadline && (input.substring(0, numDeadline) ).equals(keyDeadline) ) { //Add deadline
                extractInput = input.substring(numDeadline + 1, input.length() );
                tasks[tasksCounter] = new Deadline(extractInput);
            } else if (input.length() >= numEvent && (input.substring(0, numEvent) ).equals(keyEvent) ) { //Add event
                extractInput = input.substring(numEvent + 1, input.length() );
                tasks[tasksCounter] = new Event(extractInput);
            } else { //Add task
                extractInput = input;
                tasks[tasksCounter] = new Task(extractInput);
            }

            //Old message
            /*String preTaskMsg = "____________________________________________________________\n"
                    + "added: " + input
                    + "\n____________________________________________________________";*/

            String preTaskMsg = "____________________________________________________________\n"
                    + "Got it. I've added this task:";
            String postTaskMsg = "Now you have " + (tasksCounter + 1) + " tasks in the list."
                    + "\n____________________________________________________________";
            System.out.println(preTaskMsg);
            System.out.println(tasks[tasksCounter]);
            System.out.println(postTaskMsg);

            tasksCounter++;
        }

        modify(); //Recurring method
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        //System.out.println("Bye!");
        greet();
        try {
            modify();
        } catch (StringIndexOutOfBoundsException e) {
            String errorMessage = "____________________________________________________________\n"
                    +"OOPS!!! The description of a done/todo/deadline/event cannot be empty.\n"
                    + "____________________________________________________________\n";
            System.out.println(errorMessage);
        } catch (DukeException e) {
            String errorMessage = "____________________________________________________________\n"
                    +"OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + "____________________________________________________________\n";
            System.out.println(errorMessage);
        }
    }
}
