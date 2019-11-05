package command;

import tasklist.TaskList;
import ui.*;
import storage.*;
import error.*;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    //do you recommend abstract constructor

    /**
     * Constructor for delete
     * @param index
     */
    public DeleteCommand(int index){
        super(index);
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
