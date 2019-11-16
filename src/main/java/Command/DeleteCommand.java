package duke;

import Command.Command;
/**
 * This is the command that handles the deletion of the different task.
 *
 * @author Eunice Kwang
 */
public class DeleteCommand extends Command {
    /**
     * The task that is to be deleted.
     */
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(Tasklist tasks, UI ui, Storage storage) throws DukeException {
        // TODO Auto-generated method stub
        if(index < tasks.getSize()) {
            Task task = tasks.getTask(index);
            tasks.deleteTask(index);
            ui.showDelete(task, tasks.getSize());

            try {
                storage.save(tasks);
            } catch(DukeException e) {
                throw e;
            }
        } else
            throw new DukeException("☹ OOPS!!! The description of a delete not found.");
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

