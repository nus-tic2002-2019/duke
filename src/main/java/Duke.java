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
        taskList[Integer.parseInt(textInput)-1].markAsDone(true);
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println (taskList[Integer.parseInt(textInput)-1].getDescription());
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
        String textInput = in.nextLine();
        while (!textInput.equalsIgnoreCase("bye")){
           /* if (textInput.equalsIgnoreCase("list")) displayList(taskList);
            else if (textInput.contains("done")) {
                markInList(textInput);
            }
            //else if (textInput.equalsIgnoreCase ("bye")) break;
            else if (textInput.contains("todo")){
                String taskText = textInput.replace("todo","");
                addTodo(new Todo(taskText));
            }

            else {
                addTask(new Task(textInput), textInput);
               // dukeEcho(textInput);

            }*/
            String textInputArr[] = textInput.split(" ",2);
            switch(textInputArr[0]){
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
                    addEvent (new Event (textEvent[0], textEvent[1]));
                    break;
                default:
                    addTask(new Task(textInputArr[1]), textInput);
            }
            textInput = in.nextLine();
        }

        System.out.println("    ________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ________________________________________");
    }


}
