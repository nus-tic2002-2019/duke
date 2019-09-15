import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void addTask(Task t){
        tasks[taskCount] = t;
        System.out.println("-----------------------------------------------");
        System.out.println("added:" + t.getDescription());
        System.out.println("-----------------------------------------------");
        taskCount++;
    }

    public static void displayTasks()
    {
        System.out.println("-----------------------------------------------");
        for(int i=0; i < taskCount; i++)
        {
            System.out.println(i + 1 + ". [" + tasks[i].getStatusIcon() + "]" + tasks[i].getDescription());
        }
        System.out.println("-----------------------------------------------");
    }

    public static void markDone(int pos) {
         tasks[pos-1].markDone(true);
        System.out.println("-----------------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("    [" +  tasks[pos-1].getStatusIcon() + "]" +  tasks[pos-1].getDescription());
        System.out.println("-----------------------------------------------");
    }

    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String line;
        String [] linearr;
        while(true) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            linearr = line.split(" ", 2);
            switch (line.toUpperCase()) {
                case "LIST":
                    displayTasks();
                    break;
                case "BYE":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                default:
                    if (linearr[0].toUpperCase().equals("DONE")){
                        markDone(Integer.parseInt(linearr[1]));
                    }
                    else {
                        addTask(new Task(line));
                    }
                    break;
            }
        }
    }
}
