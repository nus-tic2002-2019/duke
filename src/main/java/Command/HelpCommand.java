package command;

import exception.DukeException;
import parser.Parser;
import storage.Storage;
import tasklist.Deadlines;
import tasklist.Events;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;

import static util.Util.convertDateTime;

public class HelpCommand extends Command {

    /**
     * Constructor for EventCommand. The class is process event related task.
     * @param commandline a string commandline contains a string of command to be processed to mark a task as done.
     */
    public HelpCommand(String commandline){
        super(commandline);
    }

    /**
     * This method is to process application help information for the user.
     *
     * @param tasks tasks object passed and used throughtout the program
     * @param ui ui object passed and used to interact with UI related object
     * @param storage storage object passed and used to interact with storage related processes
     * @throws DukeException throw any DukeException if any
     * @throws IOException throw any IOException if any
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        try {
            ui.showHelp(Parser.parseHelp(commandline));
        }
        catch (DukeException e){
            Ui.showError(e.getMessage());
        }
    }


}
