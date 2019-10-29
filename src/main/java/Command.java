import duke.exception.CommandAddException;
import duke.exception.CommandException;
import duke.exception.InvalidNumberException;
import duke.exception.NullNumberException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class Command {
    protected String c;
    protected boolean isExit;

    public Command(String fullCommand){
        this.c = fullCommand;
        this.isExit = false;
    }
    public static void CheckFirstWord(String s) throws CommandException {
        if (!(s.equals("todo" ) || s.equals( "deadline") || s.equals( "event") || s.equals( "bye")
                || s.equals( "list") || s.equals( "done") || s.equals("delete")))
            throw new CommandException();
    }

    public static void CheckNumber(int size,  int index) throws InvalidNumberException {
        if (index >= size || index < 0)
            throw new InvalidNumberException();
    }

    public static void CheckDescription (String command) throws CommandAddException {
        if (  command.equals("todo") || command.equals("deadline") || command.equals("event")  )
            throw new CommandAddException();
    }

    public static void CheckNullNumber (String command) throws NullNumberException {
        if (  command.equals("done")|| command.equals("delete") )
            throw new NullNumberException();
    }

    public void execute(ArrayList<Task> tasks, String filePath){

        String command = c.split(" ")[0].toLowerCase();
        int size = tasks.size();

        try {
            CheckFirstWord(command);
        } catch (CommandException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        switch (command){
            case "bye":
                UI.bye_message();
                isExit = true;
                break;
            case "list":
                UI.list_message(tasks, size);
                break;
            case "done":
                try {
                    CheckNullNumber(c);
                    int index = Parser.taskNumber(c);
                    CheckNumber(size, index);
                    tasks.get(index).markAsDone();
                    Storage.saveFile(filePath, tasks);
                    UI.done_message(tasks, index);
                } catch (NullNumberException e) {
                    System.out.println("☹ OOPS!!! Pls key in the number of the task");
                } catch (InvalidNumberException e) {
                    System.out.println("☹ OOPS!!! The number of task is invalid! Please key in again!");
                }
                break;
            case "delete":
                try {
                    CheckNullNumber(c);
                    int index = Parser.taskNumber(c);
                    CheckNumber(size, index);
                    UI.delete_message(tasks, index, size);
                    tasks.remove(index);
                    Storage.saveFile(filePath, tasks);
                } catch (NullNumberException e) {
                    System.out.println("☹ OOPS!!! Pls key in the number of the task");
                } catch (InvalidNumberException e) {
                    System.out.println("☹ OOPS!!! The number of task is invalid! Please key in again!");
                    UI.split_line();
                }
                break;
            case "todo":
                try {
                    CheckDescription(c);
                    String description = Parser.description(c);
                    Task t = new Todo(description);
                    tasks.add(t);
                    Storage.saveFile(filePath, tasks);
                    UI.add_message(t, size);
                } catch (CommandAddException e) {
                    System.out.println("☹ OOPS!!! Pls key in the description for the task");
                }
                break;
            case "deadline":
                try {
                    CheckDescription(c);
                    String description = Parser.description(c);
                    String time = Parser.deadlineTime(c);
                    Task t = new Deadline(description, time);
                    tasks.add(t);
                    Storage.saveFile(filePath, tasks);
                    UI.add_message(t, size);
                } catch (CommandAddException e) {
                    System.out.println("☹ OOPS!!! Pls key in the description for the task");
                }
                break;
            case "event":
                try {
                    CheckDescription(c);
                    String description = Parser.description(c);
                    String time = Parser.eventTime(c);
                    Task t = new Event(description, time);
                    tasks.add(t);
                    Storage.saveFile(filePath, tasks);
                    UI.add_message(t, size);
                } catch (CommandAddException e) {
                    System.out.println("☹ OOPS!!! Pls key in the description for the task");
                }
                break;
        }
    }
}
