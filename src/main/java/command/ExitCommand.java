package command;

import tasklist.TaskList;
import ui.*;
import storage.*;
import error.*;

public class ExitCommand extends Command {

    /**
     * Constructor
     * @param value
     */
    public ExitCommand(boolean value){
        super(value);
    }

    /**
     * Exit the program
     * @param task
     * @param ui
     * @param storage
     * @throws StringIndexOutOfBoundsException
     * @throws IllegalStringException
     */
    public void execute(TaskList task, Ui ui, Storage storage) throws StringIndexOutOfBoundsException, IllegalStringException {
        ui.showExit();
    }
}
