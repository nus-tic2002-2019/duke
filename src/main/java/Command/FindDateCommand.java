package command;

import exception.DukeException;
import parser.Parser;
import storage.Storage;
import tasklist.Deadlines;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;

public class FindDateCommand extends Command {
    /**
     *
     * @param commandline
     */
    public FindDateCommand(String commandline){

        super(commandline);
    }
    /**
     * This method is a method to be implemented by each subclass. It executes the operation for each
     * particular command supported by the application
     *
     * @param tasks   tasks object passed and used throughtout the program
     * @param ui ui object passed and used to interact with UI related object
     * @param storage storage object passed and used to interact with storage related object
     * @throws DukeException return any DukeException if any
     * @throws IOException  return any IOException if any
     */

    /**
     * This method to add Deadline tasks to the memory
     *
     * particular command supported by the application
     *
     * @param tasks   tasks object passed and used throughtout the program
     * @param ui ui object passed and used to interact with UI related object
     * @param storage storage object passed and used to interact with storage related object
     * @throws DukeException return any DukeException if any
     * @throws IOException  return any IOException if any
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        try {
            tasks.findDeadline(Parser.parseFindDate(commandline));
        }
        catch (DukeException e){
            Ui.showError(e.getMessage());
        }
    }
}
