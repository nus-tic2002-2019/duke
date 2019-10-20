import  java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static ArrayList<Task> task = new ArrayList<>();

    public static void addTask(String line) {
        if (line.startsWith("todo")) {
            String todoTask = line.substring(5);
            Todo todo = new Todo(todoTask);
            task.add(todo);
            System.out.println("Got it. I've added this task:");
            System.out.println(todo);
            System.out.println("Now you have " + task.size() + " tasks in the list");
        } else if (line.startsWith("deadline")) {
            String deadlineTask = line.substring(9, (line.indexOf("/") - 1));
            String by = line.substring((line.indexOf("/") + 4));
            Deadline deadline = new Deadline(deadlineTask, by);
            task.add(deadline);
            System.out.println("Got it. I've added this task:");
            System.out.println(deadline);
            System.out.println("Now you have " + task.size() + " tasks in the list");
        } else if (line.startsWith("event")) {
            String eventTask = line.substring(6, (line.indexOf("/") - 1));
            String at = line.substring((line.indexOf("/") + 4));
            Event event = new Event(eventTask, at);
            task.add(event);
            System.out.println("Got it. I've added this task:");
            System.out.println(event);
            System.out.println("Now you have " + task.size() + " tasks in the list");
        } else {
            Task t = new Task(line);
            task.add(t);
            System.out.println("added: " + t.getDescription());
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?");

        while (true) {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();

            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (line.equals("list")) {
                Task.listTask(task);
            } else if (line.startsWith("done")) {
                int taskItem = Integer.parseInt(line.substring(5));
                Task.doneTask(task, taskItem);
            } else {
                addTask(line);
            }
        }
    }
}
