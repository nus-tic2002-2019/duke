package Command;

import Ui.Ui;
import Tasklist.TaskList;
import Storage.Storage;
import Parser.Parser;

import java.io.IOException;
import Exception.DukeException;

public class DoneCommand extends Command{
    public DoneCommand(String commandline){
        super(commandline);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException{

        try {
            tasks.markDone(Parser.parseIntegerParameter(commandline),true);
        }
        catch (DukeException e){
            Ui.showError(e.getMessage());
        }
    }
}
