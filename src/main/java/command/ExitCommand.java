package command;

import tasklist.TaskList;
import ui.*;
import storage.*;
import error.*;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    /**
     * Constructor to exit the program
     */
    public ExitCommand(){
        super(true);
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
