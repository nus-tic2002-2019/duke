import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int tasksCounter = 0;

    public static void addToList(String input) {
        tasks[tasksCounter] = new Task(input);
        tasksCounter++;
    }

    public static void greet() {
        String greet = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________";

        System.out.println(greet);
    }

    public static void modify() {
        //Get user input
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        if (input.equals("bye") ) { //Bye
            String bye = "____________________________________________________________\n"
                    + "Bye. Hope to see you again soon!\n"
                    + "____________________________________________________________";
            System.out.println(bye);
            return;
        } else if (input.equals("list") ) { //List tasks
            System.out.println("____________________________________________________________");
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasksCounter; i++) {
                System.out.print(i + 1);
                System.out.print(".");
                System.out.print("[");
                System.out.print(tasks[i].getStatusIcon() );
                System.out.print("] ");
                System.out.println(tasks[i].getDescription() );
            }
            System.out.println("____________________________________________________________");
        } else if ( (input.substring(0, input.indexOf(' ') ) ).equals("done") ) { //Mark task as done
            //Get task index
            String taskIndex = input.substring(input.indexOf(' ') + 1, input.length());
            int taskNum = Integer.parseInt(taskIndex) - 1;

            //Set task as done
            tasks[taskNum].setDone();

            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done:");

            //Print completed task
            System.out.print("[");
            System.out.print(tasks[taskNum].getStatusIcon() );
            System.out.print("] ");
            System.out.println(tasks[taskNum].getDescription() );

            System.out.println("____________________________________________________________");
        } else { //Add to task list
            addToList(input);
            String echo = "____________________________________________________________\n"
                    + "added: " + input
                    + "\n____________________________________________________________";
            System.out.println(echo);
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
        System.out.println("Bye!");
        greet();
        modify();
    }
}
