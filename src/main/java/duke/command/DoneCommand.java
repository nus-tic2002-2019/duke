package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.others.DukeException;
import duke.others.Utility;
import duke.others.Messages;

import java.io.IOException;

/**
 * Sets a task to completed/done status.
 */
public class DoneCommand extends Command {
    protected int index;

    /**
     * @param index index of the task in the task list.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command and sets a specific task to completed/done.
     *
     * @param tasks task list.
     * @param ui text ui.
     * @param storage storage file.
     * @throws DukeException if there are no task in the list, task index input is out of range or task is already
     * in completed status.
     * @throws IOException if there are errors updating the specific line of data in the storage file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (tasks.isEmpty()) {
            throw new DukeException(Messages.LIST_EMPTY);
        } else if (tasks.size() <= index) {
            throw new DukeException("Please enter a task number between 1 and " + tasks.size());
        } else if (tasks.get(index).getIsDone()) {
            throw new DukeException("Task is already done!");
        } else {
            tasks.get(index).markAsDone();
            storage.updateLine(index, index + ";" + Utility.constructInput(tasks.get(index)));
            ui.print("One task off the list!\n\t" + tasks.get(index).getStatusIconAndDesc() + "\n");
        }
    }
}
