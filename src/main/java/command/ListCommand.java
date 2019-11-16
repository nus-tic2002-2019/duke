package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;
import exception.DukeException;

/**
 * Represents the list command.
 * */

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    /**
     * Utilizing parent class function to return descriptive list of tasks.
     * */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.getDescription();
    }
}
