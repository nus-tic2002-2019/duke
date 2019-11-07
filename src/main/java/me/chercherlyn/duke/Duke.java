package me.chercherlyn.duke;

import static me.chercherlyn.duke.ConsoleUtil.*;

//To perform input and output
import java.io.File;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.chercherlyn.duke.task.Task;
import me.chercherlyn.duke.task.tasks.Deadline;
import me.chercherlyn.duke.task.tasks.Event;
import me.chercherlyn.duke.task.tasks.Todo;

public class Duke {

    private List<Task> tasks;

    public Duke() {

        tasks = new ArrayList<>();
        loadTasks();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");

        while (true) {

            System.out.println("\n");

            //read next input and split it on 2 parts by first space
            String[] input = readLine().split("\\s+", 2);

            String command = input[0].toLowerCase();
            String[] args = new String[0];
            if (input.length > 1) args = input[1].split("\\s+");
            else args = new String[0];

            if (input.length > 1) args = input[1].split("\\s+");

            try {
                switch (command) {
                    case "bye":
                        commandBye(args);
                        break;
                    case "list":
                        commandList(args);
                        break;
                    case "done":
                        commandDone(args);
                        break;
                    case "todo":
                        commandTodo(args);
                        break;
                    case "event":
                        commandEvent(args);
                        break;
                    case "deadline":
                        commandDeadline(args);
                        break;
                    case "delete":
                        commandDelete(args);
                        break;
                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException ex) {
                printFancy("\u2639 OOPS!!! %s", ex.getMessage());
            }
        }
    }

    private void commandDelete(String[] args){
        //check - index
        if (args.length < 1 || args[0].isEmpty())
            throw new DukeException("The index of a task not specified.");

        // check : index not correct
        int index;
        try {
            index = Integer.parseInt(args[0]) - 1;
            if (index < 0 || index >= tasks.size())
                throw new DukeException("The index is out of tasks range!");
        } catch (NumberFormatException ex) {
            throw new DukeException("The index is not a number!");
        }

        Task task = tasks.get(index);
        tasks.remove(index);
        saveTasks();

        printFancy("" +
                        "Noted. I've removed this task:\n" +
                        " %s\n" +
                        "Now you have %d tasks in the list.",
                task.toString(), tasks.size());
    }

    private void commandEvent(String[] args) {
        // get "\at" index in args array
        int timeIndex = -1;
        for (int i = 0; i < args.length; i++) {
            if ("/at".equals(args[i])) {
                timeIndex = i;
                break;
            }
        }

        // check : no time specified
        if (timeIndex == -1 || args.length - 1 <= timeIndex)
            throw new DukeException("Correct \\at not specified for event.");

        // check : no description specified
        if (timeIndex == 0)
            throw new DukeException("The description of a event cannot be empty.");

        String description = String.join(" ", Arrays.copyOfRange(args, 0, timeIndex));
        String time = String.join(" ", Arrays.copyOfRange(args, timeIndex + 1, args.length));

        Task task = new Event(description, time);
        tasks.add(task);
        saveTasks();

        printFancy("" +
                        "Got it. I've added this task:\n" +
                        " %s\n" +
                        "Now you have %d tasks in the list.",
                task.toString(), tasks.size());
    }

    private void commandDeadline(String[] args) {
        // get "\by" index in args array
        int timeIndex = -1;
        for (int i = 0; i < args.length; i++) {
            if ("/by".equals(args[i])) {
                timeIndex = i;
                break;
            }
        }

        // check : no time specified
        if (timeIndex == -1 || args.length - 1 <= timeIndex)
            throw new DukeException("Correct /by not specified for deadline.");

        // check : no description specified
        if (timeIndex == 0)
            throw new DukeException("The description of a deadline cannot be empty.");

        String description = String.join(" ", Arrays.copyOfRange(args, 0, timeIndex));
        String time = String.join(" ", Arrays.copyOfRange(args, timeIndex + 1, args.length));

        Task task = new Deadline(description, time);
        tasks.add(task);
        saveTasks();

        printFancy("" +
                        "Got it. I've added this task:\n" +
                        " %s\n" +
                        "Now you have %d tasks in the list.",
                task.toString(), tasks.size());

    }

    private void commandTodo(String[] args) {
        // check : no description specified
        if (args.length < 1 || args[0].isEmpty())
            throw new DukeException("The description of a todo cannot be empty.");

        String description = String.join(" ", args);
        Task task = new Todo(description);
        tasks.add(task);
        saveTasks();

        printFancy("" +
                        "Got it. I've added this task:\n" +
                        " %s\n" +
                        "Now you have %d tasks in the list.",
                task.toString(), tasks.size());
    }

    private void commandDone(String[] args) {
        // check : index
        if (args.length < 1 || args[0].isEmpty())
            throw new DukeException("The index of a task not specified.");

        // check : index not correct
        int index;
        try {
            index = Integer.parseInt(args[0]) - 1;
            if (index < 0 || index >= tasks.size())
                throw new DukeException("The index is out of tasks range!");
        } catch (NumberFormatException ex) {
            throw new DukeException("The index is not a number!");
        }

        Task task = tasks.get(index);
        task.setDone(true);
        saveTasks();

        printFancy("Nice! I've marked this task as done:\n  %s", task.toString());
    }

    private void commandList(String[] args) {
        // if no tasks in the list, then output specified message
        if (tasks.isEmpty()) {
            printFancy("There are no tasks in the list! Add something!");
            return;
        }

        // build output list
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            int count = i + 1;
            builder.append(count).append(". ").append(tasks.get(i)).append("\n");
        }

        // remove last line break
        builder.deleteCharAt(builder.length() - 1);

        // print it
        String output = builder.toString();
        printFancy(output);
    }

    private void commandBye(String[] args) {
        printFancy("Bye. Hope to see you again soon!");
        System.exit(0); // exit the app
    }

    private void loadTasks() {
        try {
            // create folder & file if not exist
            File dir = new File("data");
            if (!dir.isDirectory()) dir.mkdir();
            File file = new File(dir, "duke.txt");
            if (!file.isFile()) file.createNewFile();

            // load data
            List<String> dataList = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            for (String data : dataList)
                tasks.add(Task.deserialize(data));
        }

        catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    private void saveTasks() {
        try {
            // create folder & file if not exist
            File dir = new File("data");
            if (!dir.isDirectory()) dir.mkdir();
            File file = new File(dir, "duke.txt");
            if (!file.isFile()) file.createNewFile();

            // build data string
            List<String> dataList = new ArrayList<>();
            for (Task task : tasks)
                dataList.add(Task.serialize(task));
            String dataListStr = String.join("\n", dataList);

            // save to file
            Files.write(file.toPath(), dataListStr.getBytes(StandardCharsets.UTF_8));
        }

        catch (Exception ex) {
            throw new IllegalStateException(ex);
        }

    }
}







