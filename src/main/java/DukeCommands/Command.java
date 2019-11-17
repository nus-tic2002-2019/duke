package newDuke.DukeCommands;

import java.io.IOException;
import newDuke.main.Storage;
import newDuke.main.TaskList;
import newDuke.main.UI;
import newDuke.DukeExceptions.Exception;

/**
 * An interface to be implemented by all the Command type objects.
 *
 * All Commands must implement an execute method, which will be accessed by the Duke.run() method.
 */

public interface Command {
    String execute(TaskList tl, Storage st) throws IOException;
}
