package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;
import exception.DukeException;


public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.getDescription();
    }
}
