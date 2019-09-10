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
        System.out.println("     added: " + reply);
        System.out.println("    ________________________________________");
    }

    public static void displayList(Task[] tasks){

        for (int index = 0; index<taskCount; index++){
            System.out.println (index+1 + "." + "[" + tasks[index].getStatusIcon() +"]" + tasks[index].getDescription());
        }
    }
    private static Task [] taskList = new Task [100];
    private static int taskCount = 0;

    public static void addTask (Task t){
        taskList[taskCount] = t;
        taskCount++;
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
        //List<String> tasks = new ArrayList<>();
        while (!textInput.equalsIgnoreCase("bye")){
            if (!textInput.equalsIgnoreCase("list") || !textInput.contains("done")){
                addTask(new Task(textInput));
                dukeEcho(textInput);
            }
            textInput = in.nextLine();
            if (textInput.equalsIgnoreCase("list")) displayList(taskList);
            if (textInput.contains("done")) {
                String[] text = textInput.split(" ", 2);
                System.out.println(Integer.parseInt(text[1]));
                taskList[Integer.parseInt(text[1])-1].markAsDone(true);
            }
            if (textInput.equalsIgnoreCase ("bye")) break;
        }
        //DukeList inputList = new DukeList();
        //System.out.println("length of List = " + inputList.getLength());

        System.out.println("    ________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ________________________________________");
    }
}
