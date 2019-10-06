import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int tCount = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String line;
        int count = 0;
        Scanner input = new Scanner(System.in);
        line = input.toString();
        String [] linePart;
        linePart = line.split(" ", 2);

        boolean isExit = false;
        while (!isExit) {
            line  = input.nextLine();
            linePart = line.split(" ", 2);
            switch (line.split(" ")[0]) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "done":
                    int line_num = Integer.parseInt(line.split(" ")[1]);
                    tasks[line_num-1].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("    [" +  tasks[line_num-1].getStatusIcon() + "]" +  tasks[line_num-1].getDescription());
                    break;
                case "todo":
                    addTask(new Todo(linePart[1]));
                    break;
                case "deadline":
                    String DeadlineArr[] = linePart[1].split("/by", 2);
                    addTask(new Deadlines(DeadlineArr[0],DeadlineArr[1])); ;
                    break;
                case "event":
                    String EventArr[] = linePart[1].split("/at", 2);
                    addTask(new Events(EventArr[0],EventArr[1])); ;
                    break;
                case "list":
                    GetList();
                    break;
                default:
                    tasks[count] = new Task(line);
                    StoreList(tasks, line, count);
                    count++;
                    addTask(new Todo(line));
                    break;
            }
        }
    }


    public static Task[] StoreList(Task[] tasks, String line, int number) {
        tasks[number] = new Task(line);
        System.out.println("added:" + line);
        return tasks;
    }

    public static void ReturnList(Task[] tasks, int number) {
        for (int i = 0; i < number; i++) {
            String icon = tasks[i].getStatusIcon();
            System.out.println(i + 1 + "." + "[" + icon + "]" + tasks[i].getDescription());
        }
    }

    public static void addTask(Task t){
        tasks[tCount] = t;
        System.out.println("-----------------------------------------------");
        System.out.println("added:" + t.getDescription());
        System.out.println("-----------------------------------------------");
        tCount++;
    }

    public static void GetList() {
        System.out.println("-----------------------------------------------");
        for (int i=0; i<tCount; i++) {
            System.out.println(i + 1 + ". " + tasks[i]);
        }
        System.out.println("-----------------------------------------------");
    }

    public static void addTask(Todo t){
        tasks[tCount] = t;
        System.out.println("-----------------------------------------------");
        System.out.println("Got it. I've added this task: :");
        System.out.println(t);
        tCount++;
        System.out.println("Now you have " + tCount + " tasks in the list");
        System.out.println("-----------------------------------------------");
    }

    public static void addTask(Deadlines t){
        tasks[tCount] = t;
        System.out.println("-----------------------------------------------");
        System.out.println("Got it. I've added this task: :");
        System.out.println(t);
        tCount++;
        System.out.println("Now you have " + tCount + " tasks in the list");
        System.out.println("-----------------------------------------------");
    }

    public static void addTask(Events t){
        tasks[tCount] = t;
        System.out.println("-----------------------------------------------");
        System.out.println("Got it. I've added this task: :");
        System.out.println(t);
        tCount++;
        System.out.println("Now you have " + tCount + " tasks in the list");
        System.out.println("-----------------------------------------------");
    }

}


