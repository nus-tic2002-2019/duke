package DukeCommands;

import java.io.IOException;
import main.Storage;
import main.TaskList;
import main.UI;
import DukeExceptions.Exception;

/**
 * An interface to be implemented by all the Command type objects.
 *
 * All Commands must implement an execute method, which will be accessed by the Duke.run() method.
 */

public interface Command {
    String execute(TaskList tl, Storage st) throws IOException;
}