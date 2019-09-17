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

    //public static void addTodo(Todo t){
    public static void addTask(Todo t){
        tasks[taskCount] = t;
        System.out.println("-----------------------------------------------");
        System.out.println("Got it. I've added this task: :");
//        System.out.println("   [T][" + t.getStatusIcon() + "] " + t.getDescription());
        System.out.println(t);
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list");
        System.out.println("-----------------------------------------------");
    }

    //public static void addDeadline(Deadlines t){
    public static void addTask(Deadlines t){
        tasks[taskCount] = t;
        System.out.println("-----------------------------------------------");
        System.out.println("Got it. I've added this task: :");
//        System.out.println("   [T][" + t.getStatusIcon() + "] " + t.getDescription());
        System.out.println(t);
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list");
        System.out.println("-----------------------------------------------");
    }

    //public static void addEvent(Events t){
    public static void addTask(Events t){
        tasks[taskCount] = t;
        System.out.println("-----------------------------------------------");
        System.out.println("Got it. I've added this task: :");
//        System.out.println("   [T][" + t.getStatusIcon() + "] " + t.getDescription());
        System.out.println(t);
        taskCount++;
        System.out.println("Now you have " + taskCount + " tasks in the list");
        System.out.println("-----------------------------------------------");
    }

    public static void displayTasks()
    {
        System.out.println("-----------------------------------------------");
        for(int i=0; i < taskCount; i++)
        {
            System.out.println(i + 1 + ". " + tasks[i]); // or tasks[i].toString()
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
//                    if (linearr[0].toUpperCase().equals("DONE")){
//                        markDone(Integer.parseInt(linearr[1]));
//                    }
//                    else {
//                        addTask(new Task(line));
//                    }
                    switch (linearr[0].toUpperCase())
                    {
                        case "DONE":
                            markDone(Integer.parseInt(linearr[1]));
                            break;
                        case "TODO":
                            //addTodo(new Todo(linearr[1]));
                            addTask(new Todo(linearr[1]));
                            break;
                        case "DEADLINE":
                            String lineDeadline[] = linearr[1].split("/by", 2);
                            //addDeadline(new Deadlines(lineDeadline[0],lineDeadline[1])) ;
                            addTask(new Deadlines(lineDeadline[0],lineDeadline[1])); ;

                            break;
                        case "EVENT":
                            String lineEvent[] = linearr[1].split("/at", 2);
                            //addEvent(new Events(lineEvent[0],lineEvent[1])); ;
                            addTask(new Events(lineEvent[0],lineEvent[1])); ;
                            break;
                        default:
//                            addTask(new Task(line));
                            //addTodo(new Todo(line));
                            addTask(new Todo(line));
                    }

                    break;
            }
        }
    }
}
