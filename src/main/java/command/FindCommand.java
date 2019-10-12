package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;
import exception.DukeException;

/**
 * Represents the find command.
 * */

public class FindCommand extends Command {

    public FindCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Utilizing parent class function to search for matching word requested by user against the task list and returning them.
     * */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        if(fullCommand.substring(5).isEmpty()){
            throw new DukeException("☹ OOPS!!! Empty description for Find");
        }
        tasks.findTask(fullCommand.substring(5));
    }
}
