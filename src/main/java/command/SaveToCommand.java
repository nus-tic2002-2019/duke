package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;

import java.io.IOException;

public class SaveToCommand extends Command {

    public SaveToCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.setPath(storage, GetPathCommand.getPath(fullCommand));
        ui.saveCopy(storage, tasks.getTaskList());
    }
}