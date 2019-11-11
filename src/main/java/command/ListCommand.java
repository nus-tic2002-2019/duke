package command;

import exception.DukeException;
import basic.Storage;
import basic.TaskList;
import basic.Ui;

/**
 * Lists all tasks in the task list to the user.
 */
public class ListCommand extends Command{
    protected static Ui ui = new Ui();

    /**
     * @param   tasks               The tasks stored in an ArrayList.
     * @param   ui                  The User Interface (UI).
     * @param   storage             The storage to allow reading and storing of tasks from and to a txt file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.sizeOfTask() == 0) {
            throw new DukeException("☹ The list is empty.");
        }
        ui.printTask(tasks);
        ui.printEmptyLine();
    }

}
