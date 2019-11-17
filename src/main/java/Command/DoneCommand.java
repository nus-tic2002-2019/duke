package command;

import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;
import exception.DukeException;

public class DoneCommand extends Command{

    /**
     * Constructor for DoneCommand class
     * @param commandline a string commandline contains a string of command to be processed to mark a task as done.
     */
    public DoneCommand(String commandline){
        super(commandline);
    }

    //public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {

    /**
     * This method is a method to mark a particular task as done
     *
     * @param tasks tasks object passed and used throughtout the program
     * @param ui ui object passed and used to interact with UI related object
     * @param storage storage object passed and used to interact with storage related processes
     * @throws DukeException throw any DukeException if any
     * @throws IOException throw any IOException if any
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException{

        try {
            tasks.markDone(Parser.parseIntegerParameter(commandline),false);
        }
        catch (DukeException e){
            Ui.showError(e.getMessage());
        }
    }
}
