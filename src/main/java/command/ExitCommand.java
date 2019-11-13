package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;

public class ExitCommand extends Command {
    public ExitCommand() {
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showEnd();
        ui.showLine();
        this.isExit = true;
    }
}
