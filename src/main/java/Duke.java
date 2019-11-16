import task.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private Storage storage;

    public void taskRecord(Task task) {
        tasks.add(task);
    }

    private Duke(String filepath) throws IOException {
        storage = new Storage(filepath);

        try {
            storage.loadFile(tasks);
        } catch (FileNotFoundException e) {
            storage.createFile();
        }
    }

    private void run() throws IOException, IndexOutOfBoundsException, NumberFormatException {
        while (true) {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            try {
                if (line.equals("exit")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (line.equals("list")) {
                    Task.listTask(tasks);
                } else if (line.startsWith("done")) {
                    int taskItem = Integer.parseInt(line.substring(5));
                    Task.doneTask(tasks, taskItem);
                } else if (line.startsWith("delete")) {
                    int taskItem = Integer.parseInt(line.substring(7));
                    Task.deleteTask(tasks, taskItem);
                    //String str = String.valueOf(taskItem);
                    //fileaccess.removeLine(((task.get(taskItem-1)).getDescription()));
                    //fileaccess.removeLine(String.valueOf(taskItem-1));
                } else if (line.startsWith("todo")) {
                    String todo = line.substring(5);

                    taskRecord(new Todo(todo));
                    (tasks.get(tasks.size() - 1)).printTask(tasks);
                    storage.writeToFile("[" + (tasks.get(tasks.size()-1)).getStatusIcon() + "]" + "[T] " + todo + System.lineSeparator());
                } else if (line.startsWith("deadline")) {
                    String deadline = line.substring(9, (line.indexOf("/") - 1));
                    String by = line.substring((line.indexOf("/") + 4));

                    taskRecord(new Deadline(deadline, by));
                    (tasks.get(tasks.size() - 1)).printTask(tasks);
                    storage.writeToFile("[" + (tasks.get(tasks.size()-1)).getStatusIcon() + "]" + "[D] " + deadline + " (by: " + by + ")" + System.lineSeparator());
                } else if (line.startsWith("event")) {
                    String event = line.substring(6, (line.indexOf("/") - 1));
                    String at = line.substring((line.indexOf("/") + 4));

                    taskRecord(new Event(event, at));
                    (tasks.get(tasks.size() - 1)).printTask(tasks);
                    storage.writeToFile("[" + (tasks.get(tasks.size()-1)).getStatusIcon() + "]" + "[E] " + event + " (at: " + at + ")" + System.lineSeparator());
                } else {
                    throw new DukeException();
                }

            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("\u2639" + " OOPS!!! The description of " + line  + " cannot be empty or incomplete. Check your input format.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\u2639" + " OOPS!!! It's either the ArrayList is empty or the index entered is out of bound.");
            } catch (NumberFormatException e) {
                System.out.println("\u2639" + " OOPS!!! Make sure the input format is correct. Check your spacing, punctuations, etc.");
            } catch (DukeException e) {
                System.out.println("\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?");

        new Duke("data/duke.txt").run();
        //Duke duke = new Duke();
        //duke.run();
    }
}
