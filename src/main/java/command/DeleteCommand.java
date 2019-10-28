package command;

import tasklist.TaskList;
import ui.*;
import storage.*;
import error.*;

public class DeleteCommand extends Command {

    //do you recommend abstract constructor
    public DeleteCommand(String command, int index){
        super(command, index);
    }

    public void execute(TaskList task, Ui ui, Storage storage) throws StringIndexOutOfBoundsException, IllegalStringException {
        ui.showDelete(task.deleteTask(index), task);
    }
}
