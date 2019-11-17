package command;

import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;
import exception.DukeException;
import java.io.IOException;

public class DeleteCommand extends Command {
    /**
     * Constructor for DeleteCommand class
     * @param commandline a string commandline contains a string of command to be processed to delete a task.
     */
    public DeleteCommand(String commandline){
        super(commandline);
    }

    //public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException, NumberFormatException {

    /**
     * This method is a method to delete a task from the tasks object (which contains a list of tasks)
     *
     * @param tasks tasks object passed and used throughtout the program
     * @param ui ui object passed and used to interact with UI related object
     * @param storage storage object passed and used to interact with storage related object
     * @throws DukeException throw any DukeException if any
     * @throws IOException throw any IOException if any
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {

        try {
            tasks.removeItem(Parser.parseIntegerParameter(commandline));
        }
        catch (DukeException e){
            Ui.showError(e.getMessage());
        }
    }
}
