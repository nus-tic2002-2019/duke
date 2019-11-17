package duke.command;

import duke.others.DukeException;
import duke.others.Utility;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Allow the users to set Do After tasks.
 */
public class SetDoAfterCommand extends Command {
    protected int parentIndex;
    protected int childIndex;

    /**
     *
     * @param parentIndex index of the parent task
     * @param childIndex index of the child task
     */
    public SetDoAfterCommand(int parentIndex, int childIndex) {
        this.parentIndex = parentIndex;
        this.childIndex = childIndex;
    }

    /**
     * Sets a task as the Do After task of another task.
     *
     * @param tasks task list.
     * @param ui text ui.
     * @param storage storage file.
     * @throws DukeException if the parent's due date is before the child's due date.
     * @throws IOException if there are errors updating the specific line of data in the storage file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (this.parentIndex >= tasks.size() || this.childIndex >= tasks.size() ||
                this.parentIndex < 0 || this.childIndex < 0) {
            throw new DukeException("Tasks number is out of range. Please select a number between 1 -" +
                    (tasks.size()+1) + ".");
        }
        if (this.parentIndex == this.childIndex) {
            throw new DukeException("Please choose 2 different tasks");
        }
        Task parentTask = tasks.get(this.parentIndex);
        Task childTask = tasks.get(this.childIndex);
        String input;
        if ((childTask.getType() != "T" && parentTask.getType() != "T") &&
                isChildDueBeforeParent(parentTask, childTask)) {
            throw new DukeException("The parent task should not be due before the child task");
        }
        if (!parentTask.isDoAfterEmpty()) {
            input = "Overwritten existing Do After task";
        }
        else {
            input = "New Do After task for " + parentTask.getDesc();
        }
        input += "\n\t[" + parentTask.getDesc() + "] -> [" + childTask.getDesc() + "]";
        parentTask.setDoAfter(this.childIndex);
        childTask.setDoBefore(this.parentIndex);
        storage.updateLine(parentIndex, parentIndex + ";" + Utility.constructInput(tasks.get(parentIndex)));
        storage.updateLine(childIndex, childIndex + ";" + Utility.constructInput(tasks.get(childIndex)));
        ui.print(input);
    }

    private boolean isChildDueBeforeParent(Task parentTask, Task childTask) {
        return childTask.getDate().isBefore(parentTask.getDate());
    }
}
