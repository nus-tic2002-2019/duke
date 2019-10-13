import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Duke {

/**********************************************************************************************************************
* Task Class [Superclass]
**********************************************************************************************************************/

    public static class Task {
        // Variables
        private String description;
        private boolean isDone;
        // Constructors.
        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }
        // Methods.
        public String getStatusIcon() {
            return (isDone ? "[\u2713]" : "[\u2718]"); // return tick or cross symbol.
        }
        public String getTask() {
            return description;
        }
        public void markAsDone() {
            isDone = true;
        }
        public String toString() { return getStatusIcon() + " " + getTask();}
    }
/**********************************************************************************************************************
* todo [Subclass] , Parent : Task
**********************************************************************************************************************/

    public static class Todo extends Task {
        // Variables.
        protected boolean isTodo;

        // Constructors.
        public Todo(String Description) {
            super(Description);
        }
        // Methods.
        public String toString() {
            return "[T]" + super.toString();
        }
        public String getTodoIcon() {return "[T]";}
    }

/**********************************************************************************************************************
* deadline [Subclass] , Parent : Task
**********************************************************************************************************************/

    public static class Deadline extends Task {
        // Variables.
        String by;
        // Constructors.
        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }
        // Methods.
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

/**********************************************************************************************************************
* Events [Subclass] , Parent : Task
**********************************************************************************************************************/
    public static class Events extends Task {
        // Variables.
        String by;

        // Constructors.
        public Events(String description, String by) {
            // Constructor
            super(description);
            this.by = by;
        }

        // Methods.
        public String toString() {
            return "[E]"
            + super.toString()
            + " (at: "
            + by
            + " ";
        }
    }

// My arraylist was of type Task. Hence to access subclasses, downcasting is required to access the subclass-methods.
    public static void downcastToTodo(Task abc) {
        Todo taskAssigned = (Todo) abc;
    }



    public static void main(String[] args) {

/**********************************************************************************************************************
 * Variables.
 **********************************************************************************************************************/

        String logo =
                  " ____        _        \n"
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
                    if(ele instanceof Todo ) {
                        downcastToTodo(ele);
                        System.out.println(ultimateStorage.indexOf(ele)+1
                            + "."
                            + ele.toString()
                        );
                    } else {
                    // This could change into a function call printList. For reuse-and better code readability.
                    System.out.println(ultimateStorage.indexOf(ele)+1 + "."
                              + ele.toString()
                            );
                    }
                } System.out.println("    " + "____________________________________________________________");

            } else if (userResponse.startsWith("done") || userResponse.startsWith("DONE") || userResponse.startsWith("Done")) {
                if(userResponse.matches(".*\\d.*")) {
                    // done 3 -> will get 3.
                    int value = Integer.parseInt(userResponse.replaceAll("[^0-9]", ""));
                    // real index in the array is in fact 2.
                    int realIndex = value - 1;
                    //for verification of the items.
                    //System.out.println(ultimateStorage.get(realIndex).getTask());
                    ultimateStorage.get(realIndex).markAsDone();
                    System.out.println(
                            graphicalFormatEnd + "\n"
                            + spaces
                            + "Nice! I've marked this task as done: \n"
                            + spaces
                            + ultimateStorage.get(realIndex).toString()
                            + graphicalFormatEnd
                            );
                }
            } else if ( userResponse.startsWith("event") || userResponse.startsWith("Event") || userResponse.startsWith("EVENT")) {

                String tempWord = "event ";
                String reSentence = userResponse.replaceAll(tempWord, "");
//                System.out.println(reSentence);
                // To filter out relevant words.
                String eventDesc = reSentence.substring(0,reSentence.indexOf("at") - 1);
                String eventAt = reSentence.substring(reSentence.indexOf("at")+ 3);
//                System.out.println(eventDesc);
//                System.out.println(eventAt);
                Events event = new Events(eventDesc,eventAt);
                ultimateStorage.add(event);

                System.out.println(
                        graphicalFormatEnd
                                + "\n"
                                + spaces + "Got it. I've added this task: \n  " + spaces
                                + ultimateStorage.get(counter).toString()
                                + "\n" + spaces + "Now you have " + ultimateStorage.size() + " tasks in the list."
                                + graphicalFormatEnd
                );
                counter++;
            }

            else if (userResponse.startsWith("todo") || userResponse.startsWith("TODO") || userResponse.startsWith("Todo")) {

                // Filter out todo and and add the rest into the array list.
                String tempWord = "todo ";
                String reSentence = userResponse.replaceAll(tempWord, "");
                Todo td = new Todo(reSentence);
                ultimateStorage.add(td);

                System.out.println(
                                graphicalFormatEnd
                                + "\n"
                                + spaces + "Got it. I've added this task: \n  " + spaces
                                + ultimateStorage.get(counter).toString()
                                + "\n" + spaces + "Now you have " + ultimateStorage.size() + " tasks in the list."
                                + graphicalFormatEnd
                );
                counter++;
            } else if (userResponse.startsWith("Deadline") || userResponse.startsWith("DEADLINE") || userResponse.startsWith("deadline")) {
  // WHOLE PORTION FOR DEADLINE. very the nabey
                String tempWord = "deadline ";
                String reSentence = userResponse.replaceAll(tempWord, "");
  // To filter out relevant words.
                String deadlineDesc = reSentence.substring(0,reSentence.indexOf("by") - 1);
                String by = reSentence.substring(reSentence.indexOf("by")+ 3);
  //System.out.println(deadlineDesc);
  //System.out.println(by);
                Deadline deadline = new Deadline(deadlineDesc,by);
                ultimateStorage.add(deadline);

                System.out.println(
                        graphicalFormatEnd
                                + "\n"
                                + spaces + "Got it. I've added this task: \n  " + spaces
                                + ultimateStorage.get(counter).toString()
                                + "\n" + spaces + "Now you have " + ultimateStorage.size() + " tasks in the list."
                                + graphicalFormatEnd
                );
                counter++;
            } else {
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
