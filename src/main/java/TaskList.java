import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Date;
/**
 * Stores all methods to deal with user command.
 * */
public class TaskList {
    /**
     * Method to deal with done.
     * @param tasks task list to be updated.
     * @param filePath file address.
     * @param fullCommand user full command.
     * */
    public static void doneCommand (ArrayList<Task> tasks, String filePath, String fullCommand) {
        tasks.get(Parser.taskNumber(fullCommand)).markAsDone();
        Storage.saveFile(filePath, tasks);
        UI.doneMessage(tasks, Parser.taskNumber(fullCommand));
    }
    /**
     * Method to deal with delete.
     * @param tasks task list to be updated.
     * @param filePath file address to be updated.
     * @param fullCommand user full command.
     * */
    public static void deleteCommand (ArrayList<Task> tasks, String filePath, String fullCommand) {
        int index = Parser.taskNumber(fullCommand);
        UI.deleteMessage(tasks, index);
        tasks.remove(index);
        Storage.saveFile(filePath, tasks);
    }
    /**
     * Method to deal with add Todo.
     * @param tasks task list to be updated.
     * @param filePath file address to be updated.
     * @param fullCommand user full command.
     * */
    public static void addTodoCommand (ArrayList<Task> tasks, String filePath, String fullCommand) {
        String description = Parser.description(fullCommand);
        Task t = new Todo(description);
        tasks.add(t);
        Storage.saveFile(filePath, tasks);
        UI.addMessage(t, tasks.size());
    }
    /**
     * Method to deal with add Deadline.
     * @param tasks task list to be updated.
     * @param filePath file address to be updated.
     * @param fullCommand user full command.
     * */
    public static void addDeadlineCommand (ArrayList<Task> tasks, String filePath, String fullCommand){
        String description = Parser.description(fullCommand);
        String date = Parser.date(fullCommand);
        if (!date.equals("0")) {
            Date by = Parser.convertDate(date);
            if (by.before(new Date())) {
                System.out.println("Deadline date cannot be earlier than now!");
            } else {
                Task t = new Deadline(description, date, by);
                tasks.add(t);
                Storage.saveFile(filePath, tasks);
                UI.addMessage(t, tasks.size());
            }
        }
    }
    /**
     * Method to deal with add Event.
     * @param tasks task list to be updated.
     * @param filePath file address to be updated.
     * @param fullCommand user full command.
     * */
    public static void addEventCommand (ArrayList<Task> tasks, String filePath, String fullCommand){
        String description = Parser.description(fullCommand);
        String date = Parser.date(fullCommand);
        if (!date.equals("0")) {
            Date at = Parser.convertDate(date);
            if (at.before(new Date())) {
                System.out.println("Event date cannot be earlier than now!");
            } else {
                Task t = new Event(description, date, at);
                tasks.add(t);
                Storage.saveFile(filePath, tasks);
                UI.addMessage(t, tasks.size());
            }
        }
    }
    /**
     * Method to deal with add find.
     * @param tasks task list for finding.
     * @param fullCommand user full command.
     * */
    public static void findCommand (ArrayList<Task> tasks, String fullCommand){
        System.out.println("Here are the matching tasks in your list:");
        int index = 1;
        String description = Parser.description(fullCommand);
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(description)) {
                System.out.println(index + "." + tasks.get(i).toString());
                index++;
            }
        }
    }

}

