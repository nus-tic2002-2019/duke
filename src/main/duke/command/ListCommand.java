package main.duke.command;

import main.duke.storage.Storage;
import main.duke.task.TaskList;
import main.duke.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printTasks();

    }
}
