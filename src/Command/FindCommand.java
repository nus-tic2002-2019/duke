package Command;

import Ui.Ui;
import Tasklist.TaskList;
import Storage.Storage;

import Exception.DukeException;

public class FindCommand extends Command {
    public FindCommand(String commandline){
        super(commandline);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.searchTask(commandline);
        }
        catch (DukeException e){
            Ui.showError(e.getMessage());
        }
    }
}
