package main.duke.command;

import main.duke.storage.Storage;
import main.duke.task.TaskList;
import main.duke.ui.Ui;

public class ExitCommand extends Command {
    @Override

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printByeMsg();
    }

    public ExitCommand() {
        bExit = true;
    }
}
