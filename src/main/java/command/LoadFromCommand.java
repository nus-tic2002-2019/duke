package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;
import exception.DukeException;

/**
 * Represents the load command.
 * */

public class LoadFromCommand extends Command {
    public LoadFromCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Utilizing parent class function to loaded file.
     * */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.setPath(storage, GetPathCommand.getPath(fullCommand));
        try {
            new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showToUser("Problem reading file. Starting with an empty task list");
        }
    }
}
