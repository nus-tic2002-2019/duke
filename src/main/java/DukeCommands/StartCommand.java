package newDuke.DukeCommands;

import newDuke.main.Storage;
import newDuke.main.TaskList;
import newDuke.main.UI;

/**
 * A Command to handle "bye" inputs resulting in a termination of the Duke program.
 */

public class StartCommand implements Command {

    public StartCommand() {

    }

    /**
     * Prints the start message via the UI method, UI.start().
     *
     * @param taskList The TaskList used to store the Tasks for this instance of Duke (not used in this method).
     * @param storage The Storage used to store the Tasks on the hard disk (not used in this method).
     */
	 
    public String execute(TaskList taskList, Storage storage) {
        return UI.start();
    }

}
