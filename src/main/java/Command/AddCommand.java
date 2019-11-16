package duke;

import Command.Command;
/**
* This is the command that handles the adding of the different task.
*
* @author Eunice Kwang
*/
public class AddCommand extends Command {
	/**
	* The task that is to be added.
	*/
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }
    /**
     * Adds the task into the tasklist, displays added task and store the task into the storage.
     * @param   tasks Array of tasks stored in an ArrayList.
     * @param   ui User Interface (UI) to allow interaction with the user.
     * @param   storage Storage allow storing and reading of tasks from file.
	 * @throws DukeException  If the application is unable to save.
     */
    @Override
    public void execute(Tasklist tasks, UI ui, Storage storage) throws DukeException {
        // TODO Auto-generated method stub
        tasks.addTask(task);
        ui.showAdd(task, tasks.getSize());

        try {
            storage.save(tasks);
        } catch(DukeException e) {
            throw e;
        }
    }

    /**
	 * Return false as system is not ready to exit.
     * @return false.
     */
	@Override
    public boolean isExit() {
        return false;
    }
}
