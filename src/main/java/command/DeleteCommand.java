package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;
import exception.DukeException;

/**
 * Represents the delete command.
 * */

public class DeleteCommand extends Command {
    /**
     * Return deadline input description.
     * */
    public DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Utilizing parent class function to delete task requested by user.
     * */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        tasks.removeTask(Integer.parseInt(fullCommand.split(" ")[1]));
        ui.changed();
    }
}
