package command;

import tasklist.TaskList;
import ui.*;
import storage.*;
import error.*;

public class DoneCommand extends Command {

    /**
     * Constructor for Done Command
     * @param command
     * @param index
     */
    public DoneCommand(String command, int index){
        super(command, index);
    }

    /**
     * Setting task to be done
     * @param task
     * @param ui
     * @param storage
     * @throws StringIndexOutOfBoundsException
     * @throws IllegalStringException
     */
    public void execute(TaskList task, Ui ui, Storage storage) throws StringIndexOutOfBoundsException, IllegalStringException {
        task.doneTask(index);
    }



}
