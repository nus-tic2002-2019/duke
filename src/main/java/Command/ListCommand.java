package Command;

import Parser.Parser;
import Storage.Storage;
import Tasklist.TaskList;
import Tasklist.Todo;
import Ui.Ui;

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
