import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Date;

public class Tasklist {

    public static void done_command (ArrayList<Task> tasks, String filePath, String fullCommand) {
        tasks.get(Parser.taskNumber(fullCommand)).markAsDone();
        Storage.saveFile(filePath, tasks);
        UI.done_message(tasks, Parser.taskNumber(fullCommand));
    }

    public static void delete_command (ArrayList<Task> tasks, String filePath, String fullCommand) {
        int index = Parser.taskNumber(fullCommand);
        UI.delete_message(tasks, index);
        tasks.remove(index);
        Storage.saveFile(filePath, tasks);
    }

    public static void addTodo_command (ArrayList<Task> tasks, String filePath, String fullCommand) {
        String description = Parser.description(fullCommand);
        Task t = new Todo(description);
        tasks.add(t);
        Storage.saveFile(filePath, tasks);
        UI.add_message(t, tasks.size());
    }


    public static void addDeadline_command (ArrayList<Task> tasks, String filePath, String fullCommand){
        String description = Parser.description(fullCommand);
        String date = Parser.date(fullCommand);
        if (!date.equals("0")) {
            Date by = Parser.converted_date(date);
            if (by.before(new Date()))
                System.out.println("Deadline date cannot be earlier than now!");
            if (by.after(new Date())) {
                Task t = new Deadline(description, date, by);
                tasks.add(t);
                Storage.saveFile(filePath, tasks);
                UI.add_message(t, tasks.size());
            }
        }
    }

    public static void addEvent_command (ArrayList<Task> tasks, String filePath, String fullCommand){
        String description = Parser.description(fullCommand);
        String date = Parser.date(fullCommand);
        if (!date.equals("0")) {
            Date at = Parser.converted_date(date);
            if (at.before(new Date()))
                System.out.println("Event date cannot be earlier than now!");
            if (at.after(new Date())) {
                Task t = new Event(description, date, at);
                tasks.add(t);
                Storage.saveFile(filePath, tasks);
                UI.add_message(t, tasks.size());
            }
        }
    }
}
