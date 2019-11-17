package command;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;
import exception.DukeException;

public class ListCommand extends Command {

    /**
     * Constructor of List Command
     * @param commandline commandline to process command
     */
    public ListCommand(String commandline){

        super(commandline);
    }

    /**
     * To display available tasks for the application
     * @param tasks tasks object passed and used throughtout the program
     * @param ui ui object passed and used to interact with UI related object
     * @param storage storage object passed and used to interact with storage related object
     * @throws DukeException throw any DukeException if any
     * @throws IOException throw any IOException if any
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        tasks.displayTasks();
    }
}
