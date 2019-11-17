package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.others.Messages;
import duke.others.DukeException;

/**
 * Display the list of tasks
 */
public class ViewAllCommand extends Command {
    public ViewAllCommand() {
    }

    /**
     * Executes the command and display all tasks in the task list.
     *
     * @param tasks task list
     * @param ui text ui.
     * @param storage storage file.
     * @throws DukeException if task list is empty
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException(Messages.LIST_EMPTY);
        }
        String input = Messages.LIST_HEADER;
        input += getAll(tasks);
        ui.print(input);
    }

    private String getAll(TaskList tasks) {
        String input = "";
        for (int i = 0; i < tasks.size(); ++i) {
            input += "\t" + (i + 1) + ". " + tasks.get(i).getStatusIconAndDesc() + "\n";
        }
        return input;
    }

}
