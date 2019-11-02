package Command;

import Storage.Storage;
import Tasklist.TaskList;
import Ui.Ui;
import Exception.DukeException;
import java.io.IOException;

public class ByeCommand extends Command{
    public ByeCommand(){
        super(commandline);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        //throw new UnsupportedOperationException("");
        //System.out.println(commandline);
        isExit=true;
    }
}
