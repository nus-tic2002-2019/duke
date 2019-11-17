package Commands;

import Exception.DukeException;
import Tasks.TaskList;
import UI.Ui;
import Storage.Storage;


/**
 *
 */
public abstract class Command {
    protected String taskItem;

    /**
     * Constructs command
     */
    public Command(){

    }

    /**
     *Constructs command
     * @param taskItem will store the command that the user input
     */
    public Command(String taskItem){
        this.taskItem = taskItem;
    }

    /**
     * Calling the function will execute the function of the individual command
     * @param tasks The task list will store the task
     * @param ui The Ui class which will help to display to the user
     * @param storage The Storage which will save the list of task to
     * @throws DukeException Any expected error
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("Error for command execute!");
    }

    /**
     * This function will be default to false until exit or bye is called.
     * @return false to keep it looping for user input
     */
    public boolean isExit(){

        return false;
    }
}
