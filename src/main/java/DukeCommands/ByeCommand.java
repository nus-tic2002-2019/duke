package newDuke.DukeCommands;

import newDuke.main.Storage;
import newDuke.main.TaskList;
import newDuke.main.UI;
/**
 * A Command to handle "bye" input resulting in a termination of the Duke program.
 */

public class ByeCommand implements Command {

    public ByeCommand() {
    }
    /**
     * Prints the bye message via the UI method, UI.bye().
     */
    public String execute(TaskList taskList, Storage storage) {
        return UI.bye();
    }
}
