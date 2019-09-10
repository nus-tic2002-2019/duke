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

    public static void displayList(List<String> tasks){
        int index = 0;
        for (String task:tasks){
            System.out.println (index+1 + "." + tasks.get(index));
            index ++;
        }
    }
    private static Task [] tasks = new Task [100];
    private static int taskCount = 0;

    
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
        List<String> tasks = new ArrayList<>();
        while (!textInput.equalsIgnoreCase("bye")){
            if (!textInput.equalsIgnoreCase("list")){
                tasks.add(textInput);
                dukeEcho(textInput);
            }
            textInput = in.nextLine();
            if (textInput.equalsIgnoreCase("list")) displayList(tasks);
            if (textInput.equalsIgnoreCase ("bye")) break;
        }
        //DukeList inputList = new DukeList();
        //System.out.println("length of List = " + inputList.getLength());

        System.out.println("    ________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ________________________________________");
    }
}
