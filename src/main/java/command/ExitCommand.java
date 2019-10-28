package command;

import tasklist.TaskList;
import ui.*;
import storage.*;
import error.*;

public class ExitCommand extends Command {
    public ExitCommand(boolean value){
        super(value);
    }

    public void execute(TaskList task, Ui ui, Storage storage) throws StringIndexOutOfBoundsException, IllegalStringException {
        ui.showExit();
    }
}
