package command;

import exception.DukeException;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;

public class SortCommand extends Command {

    public SortCommand(String commandline){
        super(commandline);
    }

    /**
     * To sort tasks based on date, applicable for events and deadline tasks
     * @param tasks   tasks object passed and used throughtout the program
     * @param ui ui object passed and used to interact with UI related object
     * @param storage storage object passed and used to interact with storage related object
     * @throws DukeException throw any DukeException if any
     * @throws IOException throw any IOException if any
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        try {
            tasks.sortTaskDate(tasks.getTaskitems());
        }
        catch (DukeException e){
            Ui.showError(e.getMessage());
        }
    }
}
