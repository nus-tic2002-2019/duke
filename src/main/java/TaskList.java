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
    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }
    /**
     * Method to deal with done.
     * @param storage file address.
     * @param fullCommand user full command.
     * */
    public void doneCommand (Storage storage, String fullCommand) {
        tasks.get(Parser.taskNumber(fullCommand)).markAsDone();
        storage.saveFile(tasks);
        UI.doneMessage(tasks, Parser.taskNumber(fullCommand));
    }
    /**
     * Method to deal with delete.
     * @param storage file address to be updated.
     * @param fullCommand user full command.
     * */
    public void deleteCommand (Storage storage, String fullCommand) {
        int index = Parser.taskNumber(fullCommand);
        UI.deleteMessage(tasks, index);
        tasks.remove(index);
        storage.saveFile(tasks);
    }
    /**
     * Method to deal with add Todo.
     * @param storage file address to be updated.
     * @param fullCommand user full command.
     * */
    public void addTodoCommand (Storage storage, String fullCommand) {
        String description = Parser.description(fullCommand);
        Task t = new Todo(description);
        tasks.add(t);
        storage.saveFile(tasks);
        UI.addMessage(t, tasks.size());
    }
    /**
     * Method to deal with add Deadline.
     * @param storage file address to be updated.
     * @param fullCommand user full command.
     * */
    public void addDeadlineCommand (Storage storage, String fullCommand) {
        String description = Parser.description(fullCommand);
        String date = Parser.date(fullCommand);
        if (!date.equals("0")) {
            Date by = Parser.convertDate(date);
            if (by.before(new Date())) {
                System.out.println("Deadline date cannot be earlier than now!");
            } else {
                Task t = new Deadline(description, date, by);
                tasks.add(t);
                storage.saveFile(tasks);
                UI.addMessage(t, tasks.size());
            }
        }
    }
    /**
     * Method to deal with add Event.
     * @param storage file address to be updated.
     * @param fullCommand user full command.
     * */
    public void addEventCommand (Storage storage, String fullCommand) {
        String description = Parser.description(fullCommand);
        String date = Parser.date(fullCommand);
        if (!date.equals("0")) {
            Date at = Parser.convertDate(date);
            if (at.before(new Date())) {
                System.out.println("Event date cannot be earlier than now!");
            } else {
                Task t = new Event(description, date, at);
                tasks.add(t);
                storage.saveFile(tasks);
                UI.addMessage(t, tasks.size());
            }
        }
    }
    /**
     * Method to deal with add find.
     * @param fullCommand user full command.
     * */
    public void findCommand (String fullCommand) {
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

