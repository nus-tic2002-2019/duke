package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public class UnknownCommand extends Command {
    public UnknownCommand() {}

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showUnknown();
    }
}
