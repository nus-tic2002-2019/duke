import duke.exception.*;
import duke.task.Task;

import java.util.ArrayList;

public class Command {
    protected String fullCommand;
    protected boolean isExit;

    public Command(String fullCommand){
        this.fullCommand = fullCommand;
        this.isExit = false;
    }

    private static void CheckFirstWord(String s) throws CommandException {
        if (!(s.equals("todo" ) || s.equals( "deadline") || s.equals( "event") || s.equals( "bye")
                || s.equals( "list") || s.equals( "done") || s.equals("delete") || s.equals("find")))
            throw new CommandException();
    }

    private static void CheckNumber(int size, int index) throws InvalidNumberException {
        if (index >= size || index < 0)
            throw new InvalidNumberException();
    }

    private static void CheckElement(String command) throws LackOfElementException {
        if (  command.equals("todo") || command.equals("deadline") || command.equals("event") ||
                command.equals("done")|| command.equals("delete") || command.equals("find"))
            throw new LackOfElementException();
    }

    public void execute(ArrayList<Task> tasks, String filePath){
        String command = Parser.command(fullCommand);
        int size = tasks.size();

        try {
            CheckFirstWord(command);
        } catch (CommandException e) {
            System.out.println("☹ OOPS!!! Pls key in the valid command");
        }

        switch (command){
            case "bye":
                UI.bye_message();
                isExit = true;
                break;
            case "list":
                UI.list_message(tasks, tasks.size());
                break;
            case "done":
                try {
                    CheckElement(fullCommand);
                    CheckNumber(size, Parser.taskNumber(fullCommand));
                    TaskList.done_command(tasks, filePath, fullCommand);
                } catch (LackOfElementException e) {
                    System.out.println("☹ OOPS!!! Pls key in the number of the task");
                } catch (InvalidNumberException e) {
                    System.out.println("☹ OOPS!!! The number of task is invalid! Please key in again!");
                }
                break;
            case "delete":
                try {
                    CheckElement(fullCommand);
                    CheckNumber(size, Parser.taskNumber(fullCommand));
                    TaskList.delete_command(tasks, filePath, fullCommand);
                } catch (LackOfElementException e) {
                    System.out.println("☹ OOPS!!! Pls key in the number of the task");
                } catch (InvalidNumberException e) {
                    System.out.println("☹ OOPS!!! The number of task is invalid! Please key in again!");
                }
                break;
            case "todo":
                try {
                    CheckElement(fullCommand);
                    TaskList.addTodo_command(tasks, filePath, fullCommand);
                } catch (LackOfElementException e) {
                    System.out.println("☹ OOPS!!! Pls key in the description for the task");
                }
                break;
            case "deadline":
                try {
                    CheckElement(fullCommand);
                    TaskList.addDeadline_command(tasks, filePath, fullCommand);
                } catch (LackOfElementException e) {
                    System.out.println("☹ OOPS!!! Pls key in the description for the task");
                }
                break;
            case "event":
                try {
                    CheckElement(fullCommand);
                    TaskList.addEvent_command(tasks, filePath, fullCommand);
                } catch (LackOfElementException e) {
                    System.out.println("☹ OOPS!!! Pls key in the description for the task");
                }
                break;
            case "find":
                try {
                    CheckElement(fullCommand);
                    TaskList.find_command(tasks, fullCommand);
                } catch (LackOfElementException e) {
                    System.out.println("☹ OOPS!!! Pls key in the description for finding");
                }
        }
    }
}
