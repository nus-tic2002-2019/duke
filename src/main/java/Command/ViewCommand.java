<<<<<<< HEAD
package Command;

import Ui.Ui;
import Tasklist.TaskList;
import Storage.Storage;

import Exception.DukeException;

public class ViewCommand extends Command {
    public ViewCommand(String commandline){
        super(commandline);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.searchSchedule(commandline);
        }
        catch (DukeException e){
            Ui.showError(e.getMessage());
        }
    }
}
=======
package Command;

import Ui.Ui;
import Tasklist.TaskList;
import Storage.Storage;

import Exception.DukeException;

public class ViewCommand extends Command {
    public ViewCommand(String commandline){
        super(commandline);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.searchSchedule(commandline);
        }
        catch (DukeException e){
            Ui.showError(e.getMessage());
        }
    }
}
>>>>>>> 052b4d35a43d775d5c0194545db262f21223b209
