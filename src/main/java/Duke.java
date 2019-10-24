import  java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static ArrayList<Task> task = new ArrayList<>();

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
            try {
                if (line.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (line.equals("list")) {
                    Task.listTask(task);
                } else if (line.startsWith("done")) {
                    int taskItem = Integer.parseInt(line.substring(5));
                    Task.doneTask(task, taskItem);
                } else if (line.startsWith("delete")) {
                    int taskItem = Integer.parseInt(line.substring(7));
                    Task.deleteTask(task, taskItem);
                } else {
                    Task.addTask(task, line);
                }
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("\u2639" + " OOPS!!! The description of a " + line  + " cannot be empty or incomplete.");
            } catch (DukeException e) {
                System.out.println("\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\u2639" + " OOPS!!! It's either the ArrayList is empty or the index entered is out of bound.");
            }
        }
    }
}
