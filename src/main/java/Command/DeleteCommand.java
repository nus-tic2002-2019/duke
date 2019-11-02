package Command;

import Parser.Parser;
import Storage.Storage;
import Tasklist.TaskList;
import Ui.Ui;
import Exception.DukeException;
import java.io.IOException;

public class DeleteCommand extends Command {
    public DeleteCommand(String commandline){

        super(commandline);
    }

    //public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException, NumberFormatException {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {

        try {
            //tasks.markDone(Parser.parseDone(commandline),true);
            tasks.removeItem(Parser.parseIntegerParameter(commandline));
        }
        catch (DukeException e){
            Ui.showError(e.getMessage());
        }
    }
}
