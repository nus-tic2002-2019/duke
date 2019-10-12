package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;
import exception.DukeException;

/**
 * Represents the done/completed command.
 * */

public class DoneCommand extends Command {

    public DoneCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Utilizing parent class function to mark completed task requested by user.
     * */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        tasks.markAsDone(Integer.parseInt(fullCommand.split(" ")[1]));
        ui.changed();
    }
}