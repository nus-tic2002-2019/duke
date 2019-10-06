import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Duke {

/**********************************************************************************************************************
* Task Class.
**********************************************************************************************************************/

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "\u2713" : "\u2718"); // return tick or cross symbol.
        }

        public String getTask() {
            return description;
        }

        public void markAsDone() {
            isDone = true;
        }
    }


    public static void main(String[] args) {

/**********************************************************************************************************************
 * Variables.
 **********************************************************************************************************************/

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String line = "____________________________________________________________";
        String spaces = "    ";
        String nextLine = "\n";
        Scanner scanInput = new Scanner(System.in);
        // 08-Sep-2019.
        Task[] content = new Task[100];
        int counter = 0;
        ArrayList<Task> ultimateStorage = new ArrayList<Task>();

/**********************************************************************************************************************
 * Formatting
 **********************************************************************************************************************/

        String graphicalFormatStart = ("    "
                + "____________________________________________________________"
                + "\n"
                + "    ");

        String graphicalFormatEnd = ("\n" + "    " + "____________________________________________________________");

        System.out.println("Hello from" + nextLine + logo);
        System.out.println(spaces + line);
        System.out.println(spaces
                +"Hello! I'm Duke"
                + nextLine + spaces
                + "What can I do for you?"
                + nextLine + spaces + line);

/**********************************************************************************************************************
 * Level One + Level Two + To be honest... all the levels are in between being mixed up..
 *
 **********************************************************************************************************************/

        while (true) {
            String userResponse = scanInput.nextLine();
            // if response is "bye"
            if (userResponse.equals("bye")) {
                break;
            }
            // if response is "list"
            else if (userResponse.equals("list")) {
                System.out.println(graphicalFormatStart);

                for(Task ele : ultimateStorage) {
                    System.out.printf(spaces); //formatting.
                    System.out.println(ultimateStorage.indexOf(ele)+1
                            + ".["
                            + ele.getStatusIcon()
                            + "] "
                            + ele.getTask()
                    );
                } System.out.println("    " + "____________________________________________________________");

            }
            else if (userResponse.contains("done") || userResponse.contains("DONE") || userResponse.contains("Done")) {
                if(userResponse.matches(".*\\d.*")) {
                    int value = Integer.parseInt(userResponse.replaceAll("[^0-9]", ""));
// For verification of digit.
// System.out.printf("Digit: %d\n", value);
                    int realIndex = value - 1;
//System.out.println(ultimateStorage.get(realIndex).getTask());
                    ultimateStorage.get(realIndex).markAsDone();
                    System.out.println(
                            graphicalFormatEnd + "\n"
                            + spaces
                            + "Nice! I've marked this task as done: \n"
                            + spaces + "["
                            + ultimateStorage.get(realIndex).getStatusIcon() + "]"
                            + ultimateStorage.get(realIndex).getTask()
                            + graphicalFormatEnd
                            );
                }
            }
            else {
                Task t = new Task(userResponse);
                ultimateStorage.add(t);
                System.out.println(
                                graphicalFormatStart
                                + "added: "
                                + ultimateStorage.get(counter).getTask()
                                + graphicalFormatEnd);
                counter++;
            }
        }
        System.out.println(graphicalFormatStart
                +"Bye. Hope to see you again soon!"
                + graphicalFormatEnd);

        content = Arrays.copyOf(content, counter);
        //System.out.println(Arrays.toString(content)); // use to check variables in arrays.
    }
}
