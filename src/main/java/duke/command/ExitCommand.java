package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Terminates the application.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
    }

    /**
     * Executes the command.
     *
     * @param tasks task list.
     * @param ui text ui.
     * @param storage storage file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showEnd();
        this.isExit = true;
    }
}
