package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * When user input/command is not taken into account in the Parser.
 */
public class UnknownCommand extends Command {
    public UnknownCommand() {}

    /**
     * Executes the command and prints a response.
     *
     * @param tasks task list.
     * @param ui text ui.
     * @param storage storage file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showUnknown();
    }
}
