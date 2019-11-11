package command;

import exception.DukeException;
import exception.EmptyException;
import basic.Storage;
import basic.TaskList;
import basic.Ui;

/**
 * Represents an executable command.
 */

public class Command {
    protected static Ui ui = new Ui();

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyException, DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means.");
    }

}


