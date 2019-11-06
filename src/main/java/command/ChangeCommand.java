package command;

import error.IllegalStringException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class ChangeCommand extends Command {

    public static final String COMMAND_WORD = "change";

    //do you recommend abstract constructor

    /**
     * Constructor for delete
     * @param index
     */
    public ChangeCommand(int index, int priorityValue){
        super(index);
        this.priority = priorityValue;
    }

    /**
     * Delete user-assigned Task
     * @param task
     * @param ui
     * @param storage
     * @throws StringIndexOutOfBoundsException
     * @throws IllegalStringException
     */
    public void execute(TaskList task, Ui ui, Storage storage) throws StringIndexOutOfBoundsException, IllegalStringException {
        task.getTask(index).setPriority(priority);
        ui.showChange(task.getTask(index));
    }
}
