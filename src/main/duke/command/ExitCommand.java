package main.duke.command;

import main.duke.storage.Storage;
import main.duke.task.TaskList;
import main.duke.ui.Ui;

public class ExitCommand extends Command {

    /**
     * Writes to savefile, then prints goodbye message on console, finally flags for console to exit.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.writeToSaveFile(tasks.listTasks());
        ui.printByeMsg();
    }

    public ExitCommand() {
        bExit = true;
    }
}
