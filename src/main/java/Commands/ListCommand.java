package Commands;

import Exception.DukeException;
import Storage.Storage;
import Tasks.TaskList;
import UI.Ui;

/**
 * Command to show the list of task the user has
 */
public class ListCommand extends Command {
    /**
     * Constructs the list command
     */
    public ListCommand(){

    }

    /**
     * Execute the list command to show all the task the user has
     * @param tasks the task list
     * @param ui the Ui
     * @param storage the Storage
     * @throws DukeException any expected error
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.getSize() <= 0) {
            throw new DukeException("There are no item in the list");
        }
        ui.showAllTask(tasks);

    }

}
