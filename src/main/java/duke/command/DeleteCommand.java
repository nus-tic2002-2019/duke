package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.others.DukeException;

import java.io.IOException;

public class DeleteCommand extends Command {
    protected int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (tasks.isEmpty()) {
            throw new DukeException("No outstanding tasks!");
        } else if (tasks.size() <= index) {
            throw new DukeException("Please enter a task number between 1 and " + tasks.size());
        } else {
            tasks.remove(index);
            storage.save(tasks);
            ui.print("Yessir! Task removed!!\n\t"
                    + tasks.get(index).getStatusIconAndDesc() + "\n" + (tasks.size() - 1) + " tasks to go!");
        }
    }
}
