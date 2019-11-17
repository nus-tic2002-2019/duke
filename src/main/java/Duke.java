import duke.task.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h1>Duke: Hello!</h1>
 * The Duke program implements an application that
 * simply displays "Hello World!" to the standard output.
 * <p>
 * The Duke Project is a Personal Assistant Chabot that
 * helps a user to keep track of various things/activities.
 * The key features of Duke are to add, delete and list tasks.
 * These tasks are classified by type either as ToDos (tasks
 * without date and time attached to it), Deadlines (tasks
 * that need to be done before a specific date & time) or
 * Events (tasks that start and end at a specific time with a
 * given date). Each task is marked with special characters
 * that (1) indicates its task type and (2) status. Other
 * interesting features involves deleting task, updating
 * task and saving task to a duke.txt file.
 *
 *
 * @author  Benjamin Barrot Rabang III
 * @version 1.0
 * @since   2019-11-17
 */
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

    private void run() throws IOException, IndexOutOfBoundsException {
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
                } else if (line.startsWith("find")) {
                    String find = line.substring(5);

                    Task.findTask(tasks, find);
                } else if (line.startsWith("todo")) {
                    String todo = line.substring(5);

                    taskRecord(new Todo(todo));
                    (tasks.get(tasks.size() - 1)).printTask(tasks);
                    storage.writeToFile("[" + (tasks.get(tasks.size()-1)).getStatusIcon() + "]" + "[T] " + todo + System.lineSeparator());
                } else if (line.startsWith("deadline")) {
                    String deadline = line.substring(9, (line.indexOf("/") - 1));
                    LocalDateTime ldt = LocalDateTime.parse(line.substring(25,35) + "T" + line.substring(36));
                    String by = ldt.format(DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm:ssa"));

                    taskRecord(new Deadline(deadline, by));
                    (tasks.get(tasks.size() - 1)).printTask(tasks);
                    storage.writeToFile("[" + (tasks.get(tasks.size()-1)).getStatusIcon() + "]" + "[D] " + deadline + " (by: " + by + ")" + System.lineSeparator());
                } else if (line.startsWith("event")) {
                    String event = line.substring(6, (line.indexOf("/") - 1));
                    LocalDateTime ldt = LocalDateTime.parse(line.substring(26,36) + "T" + line.substring(37,45));
                    LocalTime lt = LocalTime.parse(line.substring(46));
                    String at1 = ldt.format(DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm:ssa"));
                    String at2 = lt.format(DateTimeFormatter.ofPattern("hh:mm:ssa"));

                    taskRecord(new Event(event, (at1 + " - " + at2)));
                    (tasks.get(tasks.size() - 1)).printTask(tasks);
                    storage.writeToFile("[" + (tasks.get(tasks.size()-1)).getStatusIcon() + "]" + "[E] " + event + " (on: " + (at1 + " - " + at2) + ")" + System.lineSeparator());
                } else {
                    throw new DukeException();
                }

            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("\u2639" + " OOPS!!! The description of " + line  + " cannot be empty or incomplete. Check your input format.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\u2639" + " OOPS!!! It's either the ArrayList is empty or the index entered is out of bound.");
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
    }
}
