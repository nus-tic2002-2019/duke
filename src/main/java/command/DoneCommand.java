package command;

import tasklist.TaskList;
import ui.*;
import storage.*;
import error.*;

public class DoneCommand extends Command {
    public DoneCommand(String command, int index){
        super(command, index);
    }

    public void execute(TaskList task, Ui ui, Storage storage) throws StringIndexOutOfBoundsException, IllegalStringException {
        task.doneTask(index);
    }



}
