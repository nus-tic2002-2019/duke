package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.others.DukeException;

import java.io.IOException;

/**
 * Represents an executable duke.command.
 */
public class Command {
    /** Represents the status of the application. If True, application will be terminated */
    protected boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
    }

    /**
     * @return the termination status
     */
    public boolean isExit() {
        return this.isExit;
    }
}
