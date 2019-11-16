package Commands;

import Exception.DukeException;
import Storage.Storage;
import Tasks.TaskList;
import UI.Ui;

public class ListCommand extends Command {
    public ListCommand(){

    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.getSize() <= 0) {
            throw new DukeException("There are no item in the list");
        }
        ui.showAllTask(tasks);

    }

}
