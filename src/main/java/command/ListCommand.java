package command;

import tasklist.TaskList;
import ui.*;
import storage.*;
import error.*;

public class ListCommand extends Command {
    public ListCommand(){
    }

    public void execute(TaskList task, Ui ui, Storage storage) throws StringIndexOutOfBoundsException, IllegalStringException {
        ui.showToUser(task.getDescription());
    }
}
