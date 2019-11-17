package duke;

import Command.Command;
/**
 * This is the command that handles the task that is to be mark as done.
 *
 * @author Eunice Kwang
 */
public class DoneCommand extends Command {
    /**
     * The task that is to be mark as done.
     */
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Tasklist tasks, UI ui, Storage storage) throws DukeException {
        // TODO Auto-generated method stub
        if(index < tasks.getSize()) {
            Task task = tasks.getTask(index);
            tasks.markAsDone(index);
            ui.showDone(task);

            try {
                storage.save(tasks);
            } catch(DukeException e) {
                throw e;
            }
        } else
            throw new DukeException("☹ OOPS!!! The description of a marking as task is not found.");
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