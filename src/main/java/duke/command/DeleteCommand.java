package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.others.DukeException;
import duke.others.Messages;

import java.io.IOException;

/**
 * Delete a task from the task list.
 */
public class DeleteCommand extends Command {
    protected int index;

    /**
     * @param index index of the task in the task list.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Delete a specific task from the task list.
     * Set the DoAfter and DoBefore of the parent and child task to -1 (if applicable).
     *
     *
     * @param tasks task list.
     * @param ui text ui.
     * @param storage storage file.
     * @throws DukeException if the task list is empty or task index input is out of range.
     * @throws IOException if there are errors writing the data to the storage file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (tasks.isEmpty()) {
            throw new DukeException(Messages.LIST_EMPTY);
        } else if (tasks.size() <= this.index) {
            throw new DukeException("Please enter a task number between 1 and " + tasks.size());
        } else {
            updateChildAndParent(tasks);
            ui.print("Yessir! Task removed!!\n\t"
                    + tasks.get(this.index).getStatusIconAndDesc() + "\n" + (tasks.size() - 1) + " tasks to go!");
            tasks.remove(this.index);
            storage.save(tasks);
        }
    }

    private void updateChildAndParent(TaskList tasks) {
        Task currentTask = tasks.get(this.index);
        if (!currentTask.isDoBeforeEmpty()) {
            Task parentTask = tasks.get(currentTask.getDoBefore());
            parentTask.setDoAfter(-1);
        }
        if (!currentTask.isDoAfterEmpty()) {
            Task childTask = tasks.get(currentTask.getDoAfter());
            childTask.setDoBefore(-1);
        }
    }
}
