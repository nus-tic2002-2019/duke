package main.duke.command;

import main.duke.exception.DukeException;
import main.duke.storage.Storage;
import main.duke.task.TaskList;
import main.duke.ui.Ui;


public abstract class Command {

    protected boolean bExit = false;
    public boolean isExit(){
        return bExit;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
