package Command;

import Ui.Ui;
import Tasklist.TaskList;
import Storage.Storage;
import Parser.Parser;

import Exception.DukeException;
import java.io.IOException;

public class DeleteCommand extends Command {
    public DeleteCommand(String commandline){
        super(commandline);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {

        try {
            tasks.removeItem(Parser.parseIntegerParameter(commandline));
        }
        catch (DukeException e){
            Ui.showError(e.getMessage());
        }
    }
}
