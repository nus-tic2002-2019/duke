package Command;

import Ui.Ui;
import Tasklist.TaskList;
import Storage.Storage;

import java.io.IOException;
import Exception.DukeException;

public class ListCommand extends Command {
    public ListCommand(String commandline){
        super(commandline);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        tasks.displayTasks();
    }
}
