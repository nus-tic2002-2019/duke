package duke;

import Command.Command;
/**
 * This is the command that handles the exit of Duke.
 *
 * @author Eunice Kwang
 */
public class ExitCommand extends Command {

    public ExitCommand() {

    }
    /**
     * Adds the task into the tasklist, displays added task and store the task into the storage.
     * @param   tasks Array of tasks stored in an ArrayList.
     * @param   ui User Interface (UI) to allow interaction with the user.
     * @param   storage Storage allow storing and reading of tasks from file.
     * @throws DukeException  If the application is unable to save.
     */
    @Override
    public void execute(Tasklist tasks, UI ui, Storage storage) {
        // TODO Auto-generated method stub
        ui.showEnd();
    }
    /**
     * Return true as system is ready to exit.
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}