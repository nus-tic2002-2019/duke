package command;

import tasklist.TaskList;
import ui.*;
import storage.*;
import error.*;

public class DeleteCommand extends Command {

    //do you recommend abstract constructor

    /**
     * Constructor for delete
     * @param command
     * @param index
     */
    public DeleteCommand(String command, int index){
        super(command, index);
    }

    /**
     * Delete user-assigned Task
     * @param task
     * @param ui
     * @param storage
     * @throws StringIndexOutOfBoundsException
     * @throws IllegalStringException
     */
    public void execute(TaskList task, Ui ui, Storage storage) throws StringIndexOutOfBoundsException, IllegalStringException {
        ui.showDelete(task.deleteTask(index), task);
    }
}
