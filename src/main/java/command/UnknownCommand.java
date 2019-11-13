package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;

public class UnknownCommand extends Command {
    public UnknownCommand() {}

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showUnknown();
    }
}
