package command;

import tasklist.TaskList;
import ui.*;
import storage.*;
import error.*;

public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    /**
     * Constructor for Done Command
     * @param index
     */
    public DoneCommand(int index){
        super(index);
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
