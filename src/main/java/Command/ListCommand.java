package duke;

import Command.Command;

public class ListCommand extends Command {

    public ListCommand() {

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
        ui.showList(tasks);
    }
    /**
     * Return false as system is not ready to exit.
     * @return false.
     */
    @Override
    public boolean isExit() { return false; }
}
