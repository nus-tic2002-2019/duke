package command;

import tasklist.TaskList;
import ui.*;
import storage.*;
import error.*;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    /**
     * Constructor of List Command
     */
    public ListCommand(){
    }

    /**
     * To list all the Tasks in the TaskList
     * @param task
     * @param ui
     * @param storage
     * @throws StringIndexOutOfBoundsException
     * @throws IllegalStringException
     */
    public void execute(TaskList task, Ui ui, Storage storage) throws StringIndexOutOfBoundsException, IllegalStringException {
        ui.showToUser(task.getDescription());
    }
}
