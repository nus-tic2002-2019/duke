package command;

import exception.DukeException;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;

public class FindCommand extends Command {

    public FindCommand(String commandline){
        super(commandline);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        try {
            tasks.findText(Parser.parseFindText(commandline));
        }
        catch (DukeException e){
            Ui.showError(e.getMessage());
        }
    }
}
