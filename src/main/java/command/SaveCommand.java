package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;
import java.io.IOException;

/**
 * Represents the save command.
 * */

public class SaveCommand extends Command {

    public SaveCommand() {
        super();
    }

    /**
     * Utilizing parent class function to save task list.
     * */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.saveNow(storage, tasks.getTaskList());
    }
}
