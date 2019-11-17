package command;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;
import exception.DukeException;
import java.io.IOException;

public class ByeCommand extends Command{
    public ByeCommand(){
        super(commandline);
    }

    /**
     * This method is to mark the end of the program
     * @param tasks tasks object passed and used throughtout the program
     * @param ui  ui object passed and used to interact with UI related object
     * @param storage storage object passed and used to interact with storage related object
     * @throws DukeException return any DukeException if any
     * @throws IOException return any IOException if any
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        isExit=true;
    }
}
