package Command;

import Ui.Ui;
import Tasklist.TaskList;
import Storage.Storage;

import Exception.DukeException;
import java.io.IOException;

public class ByeCommand extends Command{
    public ByeCommand(){
        super(commandline);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        isExit=true;
    }
}
