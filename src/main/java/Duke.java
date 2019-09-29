import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {

    public static void dukeGreet(){
        System.out.println("    ________________________________________");
        System.out.println ("    Hello! I'm Duke");
        System.out.println ("    What can I do for you?");
        System.out.println("    ________________________________________");
    }

    public static void dukeEcho(String reply){
        System.out.println("    ________________________________________");
        System.out.println("     Got it. I've added this task: " + reply);
        System.out.println("    ________________________________________");
    }

    public static void dukeReply(Task[] taskList, int taskCount){
        System.out.println("    ________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.println("     "+ taskList[taskCount-1].getDescription());
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
        System.out.println("    ________________________________________");
    }

    public static void displayList(Task[] tasks){
        System.out.println("Here are the tasks in your list:");
        for (int index = 0; index<taskCount; index++){
            System.out.println (index+1 + "." + tasks[index].getDescription());
        }
    }
    private static Task [] taskList = new Task [100];
    private static int taskCount = 0;

    public static void addTask (Task t, String textInput){
        taskList[taskCount] = t;
        taskCount++;
        dukeEcho(textInput);

    }

    public static void addTodo (Todo td){
            taskList[taskCount] = td;
            taskCount++;
            dukeReply(taskList, taskCount);
    }

    public static void addDeadlines (Deadlines dl){
        taskList[taskCount] = dl;
        taskCount++;
        dukeReply(taskList, taskCount);
    }

    public static void addEvent (Event e){
        taskList[taskCount] = e;
        taskCount++;
        dukeReply(taskList, taskCount);
    }

    private static void markInList(String textInput) {
        try {
            taskList[Integer.parseInt(textInput) - 1].markAsDone(true);
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println (taskList[Integer.parseInt(textInput)-1].getDescription());

        } catch (NumberFormatException e){
            System.out.println ("☹ OOPS!!! You must indicate which task is done");
        }

    }

    private static void dukeBye (){
        System.out.println("    ________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ________________________________________");
    }

    private static void dukeInput (String textInput) throws DukeException {
        String textInputArr[] = textInput.split(" ",2);
        try {
            switch (textInputArr[0]) {
                case "list":
                    displayList(taskList);
                    break;
                case "done":
                    markInList(textInputArr[1]);
                    break;
                case "todo":
                    addTodo(new Todo(textInputArr[1]));
                    break;
                case "deadline":
                    String textDeadline[] = textInputArr[1].split("/by", 2);
                    addDeadlines(new Deadlines(textDeadline[0], textDeadline[1]));
                    break;
                case "event":
                    String textEvent[] = textInputArr[1].split("/at", 2);
                    addEvent(new Event(textEvent[0], textEvent[1]));
                    break;
                case "bye":
                    dukeBye();
                    break;
                default:
                    throw new DukeException(textInputArr[1]);
            }
        } catch (IndexOutOfBoundsException e){
            throw new DukeException(textInputArr[0]);
           /* switch(textInputArr[0]) {
                case "done":
                    System.out.println("☹ OOPS!!! You must indicate which task is done");
                    break;
                case "todo":
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    break;
                case "deadline":
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    break;
                case "event":
                    System.out.println("☹ OOPS!!! The description of an event cannot be empty");
                    break;
            }*/
        }
        catch (DukeException e){
            //System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        dukeGreet();
        Scanner in = new Scanner(System.in);
        String textInput;

        do {
         textInput = in.nextLine();
          try {
              dukeInput(textInput);
          } catch (DukeException e){
          }

        }  while (!textInput.equalsIgnoreCase("bye"));



    }


}
