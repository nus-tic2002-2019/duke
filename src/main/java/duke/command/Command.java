package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.others.DukeException;

import java.io.IOException;

public abstract class Command {
    protected boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public void execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;

    public boolean isExit() {
        return this.isExit;
    }
}
