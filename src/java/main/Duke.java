import exception.Exception;
import save.Save;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    //private static Task[] tasks = new Task[100];

    private static Save save;
    private static List<Task> tasks;
    private static int tCount = 0;

    public static void main(String[] args) {

        try {
            save = new Save();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            tasks = new ArrayList<>(save.loadTasks());
        } catch (IOException e) {
            e.printStackTrace();
        }
        tCount = tasks.size();

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
        String[] linePart;
        linePart = line.split(" ", 2);


        boolean isExit = false;


        while (!isExit) {
            try {
                line = input.nextLine();
                linePart = line.split(" ", 2);
                switch (line.split(" ")[0]) {
                    case "bye":
                        save.autoSave(tasks);
                        System.out.println("Bye. Hope to see you again soon!");
                        return;
                    case "done":
                        int line_num = Integer.parseInt(line.split(" ")[1]);
                        tasks.get(line_num - 1).markAsDone();
                        System.out.println(tasks.get(line_num - 1));
                        save.autoSave(tasks);

                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("    [" + tasks.get(line_num - 1).getStatusIcon() + "]" + tasks.get(line_num - 1).getDescription());
                        break;
                    case "todo":
                        if ((linePart[1].trim().isEmpty())) {
                            throw new ArrayIndexOutOfBoundsException();
                        } else {
                            addTask(new Todo(" " + linePart[1]));
                            save.autoSave(tasks);
                            break;
                        }
                    case "deadline":
                        String DeadlineArr[] = linePart[1].split("/by", 2);
                        addTask(new Deadline(" " + DeadlineArr[0], LocalDate.parse(DeadlineArr[1].trim())));
                        save.autoSave(tasks);
                        break;
                    case "event":
                        String EventArr[] = linePart[1].split("/at", 2);
                        addTask(new Event(" " + EventArr[0], LocalDate.parse(EventArr[1].trim())));
                        save.autoSave(tasks);
                        break;
                    case "delete":
                        //String Arrtasks[] = linePart[1].split("/by", 2);
                        //deleteTask(new delete(Arrtasks[0], Arrtasks[1]));
                        //System.out.println("    [" + tasks[line_num - 1].getStatusIcon() + "]" + tasks[line_num - 1].getDescription());
                        //System.out.println("    [" + tasks.get(line_num - 1).getStatusIcon() + "]" + tasks.get(line_num - 1).getDescription());
                        deleteTask(Integer.parseInt(linePart[1]) - 1);
                        save.autoSave(tasks);
                        break;
                    case "list":
                        GetList();
                        break;
                    default:
                        throw new Exception("? OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("? OOPS!!! The description of a todo cannot be empty");
            } catch (Exception e) {
                System.out.println(e);
            } catch (IOException e) {
//                e.printStackTrace();
            }
        }
    }

    //public static void addTask(Task t){
    //   tasks[tCount] = t;
    //   System.out.println("-----------------------------------------------");
    //   System.out.println("added:" + t.getDescription());
    //   System.out.println("-----------------------------------------------");
    //   tCount++;
    //}

    public static void deleteTask(int pos) {
        System.out.println("-----------------------------------------------");
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(pos));
        //System.out.println(" ["+ Arrtasks.get(pos-1).getStatusIcon() + "]" + Arrtasks.get(pos-1).toString ());
        //Arrtasks.remove(pos-1);
        tasks.remove(pos);
        tCount--;
        System.out.println("Now you have " + tCount + " tasks in the list");
        //System.out.println("Now you have " + Arrtasks.size() + " tasks in the list");
        System.out.println("-----------------------------------------------");
        //writetasks();
    }


    public static void GetList() {
        System.out.println("-----------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tCount; i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
        System.out.println("-----------------------------------------------");
    }

    public static void addTask(Todo t) {
        //tasks[tCount] = t;
        tasks.add(tCount, t);
        System.out.println("-----------------------------------------------");
        System.out.println("Got it. I've added this task:");

        System.out.println(t);
        tCount++;
        System.out.println("Now you have " + tCount + " tasks in the list");
        System.out.println("-----------------------------------------------");
    }

    public static void addTask(Deadline t) {
        //tasks[tCount] = t;
        tasks.add(tCount, t);
        System.out.println("-----------------------------------------------");
        System.out.println("Got it. I've added this task: :");
        System.out.println(t);
        tCount++;
        System.out.println("Now you have " + tCount + " tasks in the list");
        System.out.println("-----------------------------------------------");
    }

    public static void addTask(Event t) {
        // tasks[tCount] = t;
        tasks.add(tCount, t);
        System.out.println("-----------------------------------------------");
        System.out.println("Got it. I've added this task: :");
        System.out.println(t);
        tCount++;
        System.out.println("Now you have " + tCount + " tasks in the list");
        System.out.println("-----------------------------------------------");
    }

}


