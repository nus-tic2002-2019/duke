package command;

import Task.*;
import error.*;
import tasklist.TaskList;
import ui.*;
import storage.*;


public class AddCommand extends Command{

    public AddCommand(String command, String description){
        super(command, description);
    }

    public AddCommand(String command, String description, String date){
        super(command, description, date);
    }

    // do you really need to throw errors? or just inherit directly from the abstract class
    public void execute(TaskList task, Ui ui, Storage storage) throws StringIndexOutOfBoundsException, IllegalStringException {
        Task insert = null;
        switch(command) {
            case "event":
                insert = new Event(description, date);
                task.addTask(insert);
                break;
            case "deadline":
                insert = new Deadline(description, date);
                task.addTask(insert);
                break;
            case "todo":
                insert = new Todo(description);
                task.addTask(insert);
                break;
        }
        ui.showAdd(insert, task);
    }
}
